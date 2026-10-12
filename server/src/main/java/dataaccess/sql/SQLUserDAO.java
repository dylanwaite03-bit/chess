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
                throw new DataAccessException("already taken ",ex);
            }
            throw new DataAccessException("error",ex);
        }

    }

    @Override
    public UserData getUser(String username) throws DataAccessException{
        return null;
    }

    @Override
    public void clear() throws DataAccessException{

    }

}
