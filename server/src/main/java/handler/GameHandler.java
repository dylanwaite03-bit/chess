package handler;


import com.google.gson.Gson;
import service.*;

import io.javalin.http.Context;

import java.util.Map;

public class GameHandler {
    private final GameService gameService;
    private final Gson gson =new Gson();

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
            gameService.joinGame(request,authToken);
            context.status(200);
        }catch (Exception e){
            exceptionhandler(context,e);
        }
    }

    public void listGames(Context context) {
        String authToken=context.header("Authorization");

        try{
            ListGameResult result=gameService.listGames(authToken);
            context.json(result);
        } catch (Exception e) {
            exceptionhandler(context,e);
        }
    }

    public void exceptionhandler(Context context, Exception e){
        String error =e.getMessage();
        int statusCode=500;
        if ("bad request".equals(error)) statusCode=400;
        if ("unauthorized".equals(error)) statusCode=401;
        if ("already taken".equals(error)) statusCode=403;
        context.status(statusCode);
        context.json(Map.of("message","Error"+error));
    }

}
