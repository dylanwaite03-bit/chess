package passoff.service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import model.AuthData;
import org.junit.jupiter.api.Test;
import service.CreateGameRequest;
import service.CreateGameResult;
import service.GameService;
import service.JoinGameRequest;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    @Test
    public void creategamesuccess()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameRequest request=new CreateGameRequest("Chess Game");
        CreateGameResult result=gameService.createGame(request,"token123");
        assertNotNull(result);
        assertTrue(result.gameID()>0);
    }

    @Test
    public void creategameunauthorize()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);

        CreateGameRequest request=new CreateGameRequest("Chess Game");

        assertThrows(Exception.class, () ->
                gameService.createGame(request, "badtoken"));
    }

    @Test
    public void creategamebadrequest()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameRequest request=new CreateGameRequest(null);
        assertThrows(Exception.class, () ->
                gameService.createGame(request, "token123"));
    }

    @Test
    public void joingamesuccess()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameResult result=gameService.createGame(new CreateGameRequest("Chess Game"),
                "token123");
        JoinGameRequest request =
                new JoinGameRequest(result.gameID(), "WHITE");

        gameService.joinGame(request,"token123");
        assertEquals("dylan", gameDAO.getGame(result.gameID()).whiteUsername());
    }

    @Test
    public void joingameunauthorized()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameResult result =
                gameService.createGame(
                        new CreateGameRequest("Chess Game"),
                        "token123");
        JoinGameRequest request =
                new JoinGameRequest(result.gameID(), "WHITE");
        assertThrows(Exception.class, () ->
                gameService.joinGame(request, "badtoken"));
    }

    @Test
    public void joingamebadcolor()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameResult result =
                gameService.createGame(
                        new CreateGameRequest("Chess Game"),
                        "token123");
        JoinGameRequest request =
                new JoinGameRequest(result.gameID(), "brown");
        assertThrows(Exception.class, () ->
                gameService.joinGame(request, "badtoken"));
    }

    @Test
    public void joingamealreadytaken()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        authDAO.createAuth(new AuthData("token123", "dylan"));
        CreateGameResult result =
                gameService.createGame(
                        new CreateGameRequest("Chess Game"),
                        "token123");
        JoinGameRequest request =
                new JoinGameRequest(result.gameID(), "WHITE");
        gameService.joinGame(request, "token123");
        assertThrows(Exception.class, () ->
                gameService.joinGame(request, "token123"));
    }

    @Test
    public void listgamesunauthorized()throws Exception{
        GameDAO gameDAO=new MemoryGameDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameService gameService=new GameService(gameDAO,authDAO);
        assertThrows(Exception.class, () ->
                gameService.listGames("bad token"));
    }



}
