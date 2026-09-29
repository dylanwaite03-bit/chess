package dataaccess;

import model.GameData;

import java.util.HashMap;
import java.util.Map;

public class MemoryGameDAO implements GameDAO {

    private final Map<Integer, GameData> games=new HashMap<>();
    private int nextGameId=1;

    @Override
    public int createGame(GameData game) throws DataAccessException{
        int gameID=nextGameId++;
        GameData newGame= new GameData(gameID,game.gameName(), )
    }
}
