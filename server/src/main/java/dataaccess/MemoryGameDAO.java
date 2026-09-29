package dataaccess;

import model.GameData;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGameDAO implements GameDAO {

    private final Map<Integer, GameData> games=new HashMap<>();
    private int nextGameId=1;

    @Override
    public int createGame(GameData game) throws DataAccessException{
        int gameID=nextGameId++;
        GameData newGame= new GameData(gameID,game.gameName(),game.whiteUsername(),game.blackUsername(),
        game.game());
        games.put(gameID,newGame);
        return gameID;
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException{
        return games.get(gameID);
    }

    @Override
    public List<GameData> listGames() throws DataAccessException{
        return new ArrayList<>(games.values());
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException{
        games.put(game.gameId(), game);
    }

    @Override
    public void clear() throws DataAccessException{
        games.clear();
        nextGameId=1;
    }

}
