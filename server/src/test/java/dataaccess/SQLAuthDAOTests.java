package dataaccess;

import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLAuthDAO;
import model.AuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SQLAuthDAOTests {
    private SQLAuthDAO authDAO;

    @BeforeAll
    public static void createTables() throws DataAccessException{
        DbInitializer.initialize();
    }

    @BeforeEach
    public void setup() throws DataAccessException{
        authDAO=new SQLAuthDAO();
        authDAO.clear();
    }

    @Test
    public void createauthtest()throws DataAccessException{

        AuthData auth=new AuthData("token","dyl");
        authDAO.createAuth(auth);
        AuthData stored=authDAO.getAuth("token");
        assertNotNull(stored);
        assertEquals("dyl",stored.username());
        assertEquals("token",stored.authtoken());
    }

    @Test
    public void deleteauthtest()throws DataAccessException{

        AuthData auth=new AuthData("token","dyl");
        authDAO.createAuth(auth);
        authDAO.deleteAuth("token");
        AuthData stored=authDAO.getAuth("token");
        assertNull(stored);
    }


    @Test
    public void createauthtesterror()throws DataAccessException{

        authDAO.createAuth(new AuthData("token","dyl"));

        Assertions.assertThrows(DataAccessException.class, () ->
                authDAO.createAuth(new AuthData("token","dyl")));
    }

    @Test
    public void clearauthtest()throws DataAccessException{

        AuthData auth=new AuthData("token","dyl");
        authDAO.createAuth(auth);
        authDAO.clear();
        assertNull(authDAO.getAuth("token"));

    }
}
