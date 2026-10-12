package dataaccess;

import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLUserDAO;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SQLUserDAOtests {

    private SQLUserDAO userDAO;

    @Test
    public void createusertest()throws DataAccessException{
        DbInitializer.initialize();
        userDAO=new SQLUserDAO();
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
        DbInitializer.initialize();
        userDAO.createUser(new UserData("dyl","pass","email"));

        Assertions.assertThrows(DataAccessException.class, () ->
                userDAO.createUser(new UserData("dyl","pass","email")));
    }

}
