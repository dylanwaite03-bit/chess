package dataaccess;
import java.sql.SQLException;
public class DbInitializer {

    private static String Create_user_table= """
            CREATE TABLE IF NOT EXISTS users (username VARCHAR(255) NOT NULL,
            password VARCHAR(255) NOT NULL,
             VARCHAR(255) NOT NULL,
             PRIMARY KEY(username)""";



    public static void initialize() throws DataAccessException{
        DatabaseManager.createDatabase();
        try(var conn=DatabaseManager.getConnection()){
            try(var ps=conn.prepareStatement(Create_user_table)){
                ps.executeUpdate();
            }
        }catch (SQLException ex){
            throw new DataAccessException("error creating table");
        }
    }
}
