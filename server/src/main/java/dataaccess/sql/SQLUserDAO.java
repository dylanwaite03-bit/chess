package dataaccess.sql;


import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.UserDAO;
import model.UserData;

import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    @Override
    public void createUser(UserData user) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                INSERT INTO users (username,password,email)
                VALUES (?,?,?)""")) {
                preparedStatement.setString(1,user.username());
                preparedStatement.setString(2,user.password());
                preparedStatement.setString(3,user.email());
                preparedStatement.executeUpdate();
            }
        }catch(SQLException ex){
            if (ex.getErrorCode()==1062){
                throw new DataAccessException("already taken",ex);
            }
            throw new DataAccessException("error",ex);
        }

    }

    @Override
    public UserData getUser(String username) throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                SELECT username, password, email FROM users WHERE username=?""")) {
                preparedStatement.setString(1,username);
                try(var result=preparedStatement.executeQuery()){
                    if (result.next()){
                        return new UserData(result.getString("username"),result.getString("password"),
                                result.getString("email"));
                    }
                    return null;
                }
            }
        }catch(SQLException ex){
            throw new DataAccessException("error reading",ex);
        }
    }

    @Override
    public void clear() throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("""
                
                    TRUNCATE TABLE users""")) {
                preparedStatement.executeUpdate();
            }
        }catch(SQLException e){
            throw new DataAccessException("error clearing users",e);
        }
    }

}
