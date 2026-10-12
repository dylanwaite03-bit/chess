package dataaccess;

import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLUserDAO;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SQLUserDAOtests {

    private SQLUserDAO userDAO;

    @BeforeAll
    public static void createTables() throws DataAccessException{
        DbInitializer.initialize();
    }

    @BeforeEach
    public void setup() throws DataAccessException{
        userDAO=new SQLUserDAO();
        userDAO.clear();
    }

    @Test
    public void createusertest()throws DataAccessException{

        UserData user=new UserData("dyl","pass","email");
        userDAO.createUser(user);
        UserData stored=userDAO.getUser("dyl");
        assertNotNull(stored);
        assertEquals("dyl",stored.username());
        assertEquals("pass",stored.password());
        assertEquals("email",stored.email());
    }

    @Test
    public void createusertesterror()throws DataAccessException{

        userDAO.createUser(new UserData("dyl","pass","email"));

        Assertions.assertThrows(DataAccessException.class, () ->
                userDAO.createUser(new UserData("dyl","pass","email")));
    }

}
