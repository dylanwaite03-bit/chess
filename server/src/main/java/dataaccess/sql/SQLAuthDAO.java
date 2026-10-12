package dataaccess.sql;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import model.AuthData;
import model.UserData;

import java.sql.SQLException;

public class SQLAuthDAO implements AuthDAO {

    @Override
    public void createAuth(AuthData authData) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                INSERT INTO auth (username,authToken)
                VALUES (?,?)""")) {
                preparedStatement.setString(1,authData.username());
                preparedStatement.setString(2,authData.authtoken());
                preparedStatement.executeUpdate();
            }
        }catch(SQLException ex){
            throw new DataAccessException("error creating auth",ex);
        }
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                SELECT username, authToken FROM auth WHERE username=?""")) {
                preparedStatement.setString(1,authToken);
                try(var result=preparedStatement.executeQuery()){
                    if (result.next()){
                        return new AuthData(result.getString("username"),result.getString("authToken"));
                    }
                    return null;
                }
            }
        }catch(SQLException ex){
            throw new DataAccessException("error reading",ex);
        }
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                DELETE FROM auth WHERE
                authToken=?""")) {
                preparedStatement.setString(1,authToken);
                preparedStatement.executeUpdate();
            }
        }catch(SQLException ex){
            throw new DataAccessException("error deleting auth",ex);
        }

    }

    @Override
    public void clear() throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                
                    TRUNCATE TABLE auth""")) {
                preparedStatement.executeUpdate();
            }
        }catch(SQLException e){
            throw new DataAccessException("error clearing auth",e);
        }

    }


}
