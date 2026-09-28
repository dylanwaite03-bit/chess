package server;

import io.javalin.*;
import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import handler.UserHandler;
import io.javalin.json.JavalinGson;
import service.UserService;
import handler.ClearHandler;
import service.ClearService;

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

        UserService userService=new UserService(userDAO,authDAO);
        UserHandler userHandler=new UserHandler(userService);

        ClearService clearService=new ClearService(userDAO,authDAO);
        ClearHandler clearHandler=new ClearHandler(clearService);

        javalin.post("/user", userHandler::register);
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
