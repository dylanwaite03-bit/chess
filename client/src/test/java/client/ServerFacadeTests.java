package client;

import model.GameData;
import org.junit.jupiter.api.*;
import server.Server;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;


public class ServerFacadeTests {

    private static Server server;
    private static int port;
    private static ServerFacade facade=new ServerFacade(port);

    @BeforeAll
    public static void init() {
        server = new Server();
        port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeEach
    public void clearfacade()throws Exception{
        facade=new ServerFacade(port);
        facade.clearDb();
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void registerGoodTest()throws Exception{
        String response=facade.register("dyl","pass","email");
        assertTrue(facade.authenticated());
        assertTrue(response.contains("dyl"));
    }

    @Test
    public void registerBadTest()throws Exception{
        facade.register("dyl","pass","email");
        ServerFacade secondPerson=new ServerFacade(port);
        assertThrows(Exception.class,() -> secondPerson.register("dyl","pass","email"));
        assertFalse(secondPerson.authenticated());
    }


    @Test
    public void logoutGoodTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.logout();
        assertFalse(facade.authenticated());
    }

    @Test
    public void loginGoodTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.logout();
        facade.login("dyl","pass");
        assertTrue(facade.authenticated());

    }

    @Test
    public void loginbadTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.logout();
        assertThrows(Exception.class,() -> facade.login("dyl","ps"));
        assertFalse(facade.authenticated());
    }

    @Test
    public void createGameGoodTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.createGame("coolgame");
        assertTrue(facade.authenticated());
        Map<Integer, GameData> gamelist=facade.listGames();
        assertEquals(gamelist.size(),1);
    }

    @Test
    public void joinGameGoodTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.createGame("coolgame");
        facade.joinGame(1,"WHITE");
        GameData game =facade.listGames().get(1);
        assertEquals("dyl",game.whiteUsername());
    }

    @Test
    public void joinGameBadTest()throws Exception{
        facade.register("dyl","pass","email");
        facade.createGame("coolgame");
        facade.joinGame(1,"WHITE");
        ServerFacade secondPerson=new ServerFacade(port);
        secondPerson.register("bill","ps","email2");
        secondPerson.joinGame(1,"WHITE");
        GameData game =facade.listGames().get(1);
        assertEquals("dyl",game.whiteUsername());
    }


}
