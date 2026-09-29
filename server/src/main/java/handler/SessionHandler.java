package handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import service.LoginRequest;
import service.LoginResult;
import service.SessionService;

public class SessionHandler {
    private final SessionService sessionService;
    private final Gson gson=new Gson();

    public SessionHandler(SessionService sessionService) {
        this.sessionService=sessionService;
    }

    public void login(Context context) throws Exception{
        LoginRequest request= gson.fromJson(context.body(), LoginRequest.class);
        LoginResult result=sessionService.login(request);
        context.json(result);

    }

    public void logout(Context context) throws Exception{
        String authtoken=context.header("Authorization");
        sessionService.logout(authtoken);
        context.status(200);
    }
}
