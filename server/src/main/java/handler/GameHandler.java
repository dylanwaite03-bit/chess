package handler;


import com.google.gson.Gson;
import service.GameService;

public class GameHandler {
    private final GameService gameService;
    private final Gson gson =new Gson();

    public GameHandler(GameService gameService){
        this.gameService=gameService;
    }

}
