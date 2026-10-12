package dataaccess.sql;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import model.AuthData;

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
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException{

    }

    @Override
    public void clear() throws DataAccessException{

    }


}
