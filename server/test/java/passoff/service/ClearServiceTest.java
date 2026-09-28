package passoff.service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import service.ClearService;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

    @Test
    public void clearsuccess() throws Exception{
        UserDAO userDAO= new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        ClearService clearService=new ClearService(userDAO,authDAO);

        userDAO.createUser(new UserData("dylan", "password", "email@e.com"));

        authDAO.createAuth(new AuthData("token123", "dylan"));

        clearService.clear();

        assertNull(userDAO.getUser("dylan"));
        assertNull(authDAO.getAuth("token123"));

    }
}
