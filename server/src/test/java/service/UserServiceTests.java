package service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import model.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTests {

    @Test
    public void registerSuccess() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        UserService userService=new UserService(userDAO,authDAO);

        RegisterRequest request=new RegisterRequest("dylan","password","email@e.com");

        RegisterResult result= userService.register(request);

        assertEquals("dylan",result.username());
        assertNotNull(result.authToken());
    }

    @Test
    public void registerAlreadyTaken() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        UserService userService=new UserService(userDAO,authDAO);

        UserData existinguser=new UserData("dylan","password","email@e.com");
        userDAO.createUser(existinguser);

        RegisterRequest request=new RegisterRequest("dylan","password","email@e.com");

        assertThrows(Exception.class, () -> userService.register(request));
    }

    @Test
    public void registerbadrequest() throws Exception{
        UserDAO userDAO=new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        UserService userService=new UserService(userDAO,authDAO);

        RegisterRequest request=new RegisterRequest(null,"password","email@e.com");

        assertThrows(Exception.class, () -> userService.register(request));
    }

}
