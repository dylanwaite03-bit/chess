package dataaccess.sql;

import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;

import java.util.List;

public class SQLGameDAO implements GameDAO {

    @Override
    public void clear() throws DataAccessException{

    }

    @Override
    public int createGame(GameData game) throws DataAccessException{
        return 0;
    }

    @Override
    public GameData getGame(int gameId) throws DataAccessException{
        return null;
    }

    @Override
    public List<GameData> listGames() throws DataAccessException{
        return null;
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException{

    }

}
