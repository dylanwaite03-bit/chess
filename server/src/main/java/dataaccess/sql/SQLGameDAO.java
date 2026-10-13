package dataaccess.sql;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.GameDAO;
import model.GameData;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import model.UserData;

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
                INSERT INTO games (whiteUsername,blackUsername,gameName,game)
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
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                SELECT gameID,whiteUsername,blackUsername,gameName,game FROM games WHERE game_ID=?""")) {
                preparedStatement.setInt(1,gameId);
                try(var result=preparedStatement.executeQuery()){
                    if (result.next()){
                        return new GameData(result.getInt("gameID"),result.getString("whiteUsername"),
                                result.getString("blackUsername"),result.getString("gameName"),
                                gson.fromJson(result.getString("game"), ChessGame.class));
                    }
                    return null;
                }
            }
        }catch(SQLException ex){
            throw new DataAccessException("error reading",ex);
        }
    }

    @Override
    public List<GameData> listGames() throws DataAccessException{
        List<GameData> games=new ArrayList<GameData>();
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                SELECT gameID,whiteUsername,blackUsername,gameName,game FROM games""")) {
                try(var result=preparedStatement.executeQuery()){
                    if (result.next()){
                        while(result.next()) {
                            games.add(new GameData(result.getInt("gameID"), result.getString("whiteUsername"),
                                    result.getString("blackUsername"), result.getString("gameName"),
                                    gson.fromJson(result.getString("game"), ChessGame.class)));
                        }
                        return games;
                    }
                    return null;
                }
            }
        }catch(SQLException ex){
            throw new DataAccessException("error listing games",ex);
        }
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                UPDATE games SET whiteUsername=?,blackUsername=?,gameName=?,game=? WHERE gameID=?""")) {
                preparedStatement.setString(1,game.whiteUsername());
                preparedStatement.setString(2, game.blackUsername());
                preparedStatement.setString(3, game.gameName());
                preparedStatement.setString(4, gson.toJson(game.game()));
                preparedStatement.setInt(5,game.gameID());
                preparedStatement.executeUpdate();
            }
        }catch(SQLException ex){
            throw new DataAccessException("error updating",ex);
        }

    }

}
