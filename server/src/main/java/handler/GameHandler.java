package handler;


import com.google.gson.Gson;
import service.CreateGameRequest;
import service.CreateGameRequest;
import service.CreateGameResult;
import service.GameService;

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
        CreateGameResult result=gameService.createGame(request,authToken);
        context.json(result);
    }

}
