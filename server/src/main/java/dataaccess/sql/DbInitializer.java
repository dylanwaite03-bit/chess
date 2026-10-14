package dataaccess.sql;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;

import java.sql.SQLException;
public class DbInitializer {

    private static String CREATE_USER_TABLE= """
            CREATE TABLE IF NOT EXISTS users (username VARCHAR(255) NOT NULL,
            password VARCHAR(255) NOT NULL,
            email VARCHAR(255) NOT NULL,
             PRIMARY KEY(username))""";

    private static String CREATE_AUTH_TABLE= """
            CREATE TABLE IF NOT EXISTS auth (authToken VARCHAR(255) NOT NULL,
            username VARCHAR(255) NOT NULL,
            PRIMARY KEY(authToken))""";

    private static String CREATE_GAME_TABLE= """
            CREATE TABLE IF NOT EXISTS games (
            gameID INT NOT NULL AUTO_INCREMENT,
            whiteUsername VARCHAR(255),
            blackUsername VARCHAR(255),
            gameName VARCHAR(255) NOT NULL,
            game LONGTEXT NOT NULL,
            PRIMARY KEY(gameID))""";



    public static void initialize() throws DataAccessException {
        DatabaseManager.createDatabase();
        try(var conn=DatabaseManager.getConnection()){
            try(var ps=conn.prepareStatement(CREATE_USER_TABLE)){
                ps.executeUpdate();
            }
            try(var ps=conn.prepareStatement(CREATE_AUTH_TABLE)){
                ps.executeUpdate();
            }
            try(var ps=conn.prepareStatement(CREATE_GAME_TABLE)){
                ps.executeUpdate();
            }
        }catch (SQLException ex){
            throw new DataAccessException("error creating table", ex);
        }
    }
}
