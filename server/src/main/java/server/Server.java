package server;

import dataaccess.*;
import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLAuthDAO;
import dataaccess.sql.SQLGameDAO;
import dataaccess.sql.SQLUserDAO;
import handler.GameHandler;
import io.javalin.*;
import com.google.gson.Gson;
import io.javalin.json.JavalinGson;
import service.GameService;
import handler.SessionHandler;
import service.SessionService;

public class Server {

    private final Javalin javalin;

    public Server() {

        try {
            DbInitializer.initialize();
        }catch (DataAccessException e){
            throw new RuntimeException("unable to start database");
        }

        Gson gson=new Gson();

        javalin = Javalin.create(config -> {
                    config.staticFiles.add("web");
                    config.jsonMapper(new JavalinGson());
                });

        UserDAO userDAO= new SQLUserDAO();
        AuthDAO authDAO=new SQLAuthDAO();
        GameDAO gameDAO=new SQLGameDAO();


        SessionService sessionService=new SessionService(userDAO, authDAO,gameDAO);
        SessionHandler sessionHandler=new SessionHandler(sessionService);

        GameService gameService=new GameService(gameDAO, authDAO);
        GameHandler gameHandler=new GameHandler(gameService);


        javalin.post("/user", sessionHandler::register);
        javalin.post("/session", sessionHandler::login);
        javalin.post("/game", gameHandler::createGame);
        javalin.put("/game",gameHandler::joinGame);
        javalin.get("/game",gameHandler::listGames);
        javalin.delete("/session",sessionHandler::logout);
        javalin.delete("/db",sessionHandler::clear);
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
