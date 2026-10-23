package client;

import org.junit.jupiter.api.*;
import server.Server;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade facade=new ServerFacade();

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeEach
    public void clearfacade(){
        facade=new ServerFacade();
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
        ServerFacade secondPerson=new ServerFacade();
        secondPerson.register("dyl","pass","email");
        assertFalse(facade.authenticated());
    }

}
