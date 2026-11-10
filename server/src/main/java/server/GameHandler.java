package server;


import chess.ChessGame;
import com.google.gson.Gson;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import model.GameData;
import service.*;

import io.javalin.http.Context;
import websocket.commands.UserGameCommand;
import websocket.messages.ServerMessage;

import java.util.*;

public class GameHandler {
    private final GameService gameService;
    private final Gson gson =new Gson();
    private Map<Integer, Set<WsContext>> connections=new HashMap<>();

    public GameHandler(GameService gameService){
        this.gameService=gameService;
    }

    public void createGame(Context context) {
        CreateGameRequest request= gson.fromJson(context.body(),CreateGameRequest.class);
        String authToken=context.header("Authorization");
        try {
            CreateGameResult result = gameService.createGame(request, authToken);
            context.json(result);
        } catch (Exception e){
            exceptionhandler(context,e);
        }
    }

    public void joinGame(Context context){
        JoinGameRequest request= gson.fromJson(context.body(),JoinGameRequest.class);
        String authToken= context.header("Authorization");
        try{
            GameData game=gameService.joinGame(request,authToken);
            context.json(game);
        }catch (Exception e){
            exceptionhandler(context,e);
        }
    }

    public void listGames(Context context) {
        String authToken=context.header("Authorization");

        try{
            List<GameData> result=gameService.listGames(authToken);
            context.json(Map.of("games",result));
        } catch (Exception e) {
            exceptionhandler(context,e);
        }
    }

    public void exceptionhandler(Context context, Exception e){
        String error =e.getMessage();
        int statusCode=500;
        if ("bad request".equals(error))
            {statusCode=400;}
        if ("unauthorized".equals(error))
            {statusCode=401;}
        if ("already taken".equals(error))
            { statusCode=403;}
        context.status(statusCode);
        context.json(Map.of("message","Error"+error));
    }

    public void messageHandler(WsMessageContext context){
        String message=context.message();
        UserGameCommand userGameCommand=gson.fromJson(message,UserGameCommand.class);
        System.out.println(context.message());
        if (userGameCommand.getCommandType()== UserGameCommand.CommandType.CONNECT){
            try {
                GameData game = gameService.getGame(userGameCommand.getAuthToken(), userGameCommand.getGameID());
                Set<WsContext> gameConnections=connections.get(userGameCommand.getGameID());
                if (gameConnections==null){
                    gameConnections=new HashSet<>();
                    connections.put(userGameCommand.getGameID(), gameConnections);
                }
                ServerMessage response=new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME, game,null,null);
                context.send(gson.toJson(response));
                ServerMessage notification=new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION,null,"player joined",null);
                for(WsContext connection:gameConnections){
                    connection.send(gson.toJson(notification));
                }
                gameConnections.add(context);
            }catch (Exception e){
                ServerMessage Message=new ServerMessage(ServerMessage.ServerMessageType.ERROR,null,null,"error connecting");
                context.send(gson.toJson(Message));
            }
        }
        if (userGameCommand.getCommandType()== UserGameCommand.CommandType.MAKE_MOVE) {
            try{
                GameData game = gameService.makeMove(userGameCommand.getAuthToken(), userGameCommand.getGameID(),
                        userGameCommand.getMove());
                ServerMessage response=new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME,game,null,null);
                Set<WsContext> gameConnections=connections.get(userGameCommand.getGameID());
                for(WsContext connection:gameConnections){
                    connection.send(gson.toJson(response));
                }

            }catch(Exception e){
                e.printStackTrace();
                ServerMessage Message=new ServerMessage(ServerMessage.ServerMessageType.ERROR,null,null,"error making move");
                context.send(gson.toJson(Message));
            }
        }


    }


    
}
