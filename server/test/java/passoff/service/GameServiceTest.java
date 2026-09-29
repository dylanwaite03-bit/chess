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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


}
