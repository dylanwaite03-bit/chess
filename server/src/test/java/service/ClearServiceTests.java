package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import model.GameData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTests {

    @Test
    public void clearsuccess() throws Exception{
        UserDAO userDAO= new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        ClearService clearService=new ClearService(userDAO,authDAO,gameDAO);

        userDAO.createUser(new UserData("dylan", "password", "email@e.com"));

        authDAO.createAuth(new AuthData("token123", "dylan"));
        GameData game = new GameData(
                1,
                "dylan",
                null,
                "Chess Game",
                null);

        gameDAO.createGame(game);

        clearService.clear();

        assertNull(userDAO.getUser("dylan"));
        assertNull(authDAO.getAuth("token123"));
        assertNull(gameDAO.getGame(1));

    }
}
