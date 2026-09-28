package passoff.service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import org.junit.jupiter.api.Test;
import service.RegisterRequest;
import service.RegisterResult;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

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



}
