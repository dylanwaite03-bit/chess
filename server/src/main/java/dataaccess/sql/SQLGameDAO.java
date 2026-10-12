package dataaccess.sql;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.GameDAO;
import model.GameData;

import java.sql.SQLException;
import java.util.List;

public class SQLGameDAO implements GameDAO {

    @Override
    public void clear() throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                
                    TRUNCATE TABLE games""")) {
                preparedStatement.executeUpdate();
            }
        }catch(SQLException e){
            throw new DataAccessException("error clearing games",e);
        }


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
