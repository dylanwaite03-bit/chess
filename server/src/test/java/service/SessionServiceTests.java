package service;

import dataaccess.*;
import dataaccess.sql.SQLAuthDAO;
import dataaccess.sql.SQLUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTests {

    @Test
    public void successlogin() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);
        String hashedpassword= BCrypt.hashpw("password",BCrypt.gensalt());
        UserData user= new UserData( "dylan",
                hashedpassword,
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
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);
        UserData user= new UserData( "dylan",
                "password",
                "email@e.com");

        userDAO.createUser(user);
        LoginRequest request=new LoginRequest("dylan", "wrongpassword");

        assertThrows(Exception.class, () -> sessionService.login(request));
    }

    @Test
    public void wrongusernamelogin() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);
        UserData user= new UserData( "dylan",
                "password",
                "email@e.com");

        userDAO.createUser(user);
        LoginRequest request=new LoginRequest("dyl", "password");

        assertThrows(Exception.class, () -> sessionService.login(request));
    }

    @Test
    public void logoutsuccessfull() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);
        AuthData authData = new AuthData("token123", "dylan");

        authDAO.createAuth(authData);
        sessionService.logout("token123");

        assertNull(authDAO.getAuth("token123"));
    }

    @Test
    public void logoutinvalid()throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);

        assertThrows(Exception.class, () -> sessionService.logout("badtoken"));
    }

    @Test
    public void badrequestlogin()throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        SessionService sessionService=new SessionService(userDAO,authDAO,gameDAO);

        LoginRequest request=new LoginRequest(null,"password");

        assertThrows(Exception.class, () -> sessionService.login(request));
    }

}
