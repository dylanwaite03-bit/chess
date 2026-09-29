package passoff.service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import service.LoginRequest;
import service.LoginResult;
import service.SessionService;

import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest {

    @Test
    public void successlogin() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO);
        UserData user= new UserData( "dylan",
                "password",
                "email@e.com");

        userDAO.createUser(user);
        LoginRequest request=new LoginRequest("dylan", "password");
        LoginResult result=sessionService.login(request);
        assertEquals("dylan",result.username());
        assertNotNull(result.authToken());
    }

    @Test
    public void wrongpasswordlogin() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO);
        UserData user= new UserData( "dylan",
                "password",
                "email@e.com");

        userDAO.createUser(user);
        LoginRequest request=new LoginRequest("dylan", "wrongpassword");

        assertThrows(Exception.class, () -> sessionService.login(request));
    }

}
