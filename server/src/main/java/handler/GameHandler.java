package handler;


import com.google.gson.Gson;
import service.*;

import io.javalin.http.Context;

public class GameHandler {
    private final GameService gameService;
    private final Gson gson =new Gson();

    public GameHandler(GameService gameService){
        this.gameService=gameService;
    }

    public void createGame(Context context) throws Exception{
        CreateGameRequest request= gson.fromJson(context.body(),CreateGameRequest.class);
        String authToken=context.header("Authorization");
        try {
            CreateGameResult result = gameService.createGame(request, authToken);
            context.json(result);
        } catch (Exception e){
            if(e.getMessage().equals("bad request")){
                context.status(400);
                context.json("{\"message\":\"Error: bad request\"}");
            }
            if(e.getMessage().equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
        }
    }

    public void joinGame(Context context)throws Exception{
        JoinGameRequest request= gson.fromJson(context.body(),JoinGameRequest.class);
        String authToken= context.header("Authorization");
        try{
            gameService.joinGame(request,authToken);
            context.status(200);
        }catch (Exception e){
            if(e.getMessage().equals("bad request")){
                context.status(400);
                context.json("{\"message\":\"Error: bad request\"}");
            }
            if(e.getMessage().equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
            if(e.getMessage().equals("already taken")){
                context.status(403);
                context.json("{\"message\":\"Error: already taken\"}");
            }
        }
    }

    public void listGames(Context context) throws Exception{
        String authToken=context.header("Authorization");

        try{
            ListGameResult result=gameService.listGames(authToken);
            context.json(result);
        } catch (Exception e) {
            if(e.getMessage().equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
        }
    }

}
