package server;

import dataaccess.*;
import handler.GameHandler;
import io.javalin.*;
import com.google.gson.Gson;
import handler.UserHandler;
import io.javalin.json.JavalinGson;
import service.GameService;
import service.UserService;
import handler.ClearHandler;
import service.ClearService;
import handler.SessionHandler;
import service.SessionService;

public class Server {

    private final Javalin javalin;

    public Server() {
        Gson gson=new Gson();

        javalin = Javalin.create(config -> {
                    config.staticFiles.add("web");
                    config.jsonMapper(new JavalinGson());
                });

        UserDAO userDAO= new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();
        GameDAO gameDAO=new MemoryGameDAO();

        UserService userService=new UserService(userDAO,authDAO);
        UserHandler userHandler=new UserHandler(userService);

        SessionService sessionService=new SessionService(userDAO, authDAO);
        SessionHandler sessionHandler=new SessionHandler(sessionService);

        GameService gameService=new GameService(gameDAO, authDAO);
        GameHandler gameHandler=new GameHandler(gameService);

        ClearService clearService=new ClearService(userDAO,authDAO);
        ClearHandler clearHandler=new ClearHandler(clearService);

        javalin.post("/user", userHandler::register);
        javalin.post("/session", sessionHandler::login);
        javalin.post("/game", gameHandler::createGame);
        javalin.delete("/session",sessionHandler::logout);
        javalin.delete("/db",clearHandler::clear);
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
