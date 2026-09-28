package server;

import io.javalin.*;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import handler.UserHandler;
import service.UserService;
import handler.ClearHandler;
import service.ClearService;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        UserDAO userDAO= new MemoryUserDAO();
        AuthDAO authDAO=new MemoryAuthDAO();

        UserService userService=new UserService(userDAO,authDAO);
        UserHandler userHandler=new UserHandler(userService);

        ClearService clearService=new ClearService(userDAO,authDAO);
        ClearHandler clearHandler=new ClearHandler(clearService);

        javalin.post("/user", userHandler::register);
        javalin.post("/db",clearHandler::clear);
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
