package dataaccess.sql;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.GameDAO;
import model.GameData;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.google.gson.Gson;

public class SQLGameDAO implements GameDAO {

    private Gson gson=new Gson();

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
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                INSERT INTO games (whiteUsername,blackUsername,gameName,name)
                VALUES (?,?,?,?)""", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1,game.whiteUsername());
                preparedStatement.setString(2, game.blackUsername());
                preparedStatement.setString(3, game.gameName());
                preparedStatement.setString(4, gson.toJson(game.game()));
                preparedStatement.executeUpdate();
                try(var keys=preparedStatement.getGeneratedKeys()){
                    if(keys.next()){
                        return keys.getInt(1);
                    }
                    throw new DataAccessException("error creating game");
                }
            }
        }catch(SQLException ex){
            throw new DataAccessException("error",ex);
        }
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
