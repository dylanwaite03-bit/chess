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
        try{
        LoginResult result=sessionService.login(request);
        context.json(result);
        } catch (Exception e){
            if(e.getMessage().equals("bad request")){
                context.status(400);
                context.json("{\"message\":\"Error: bad request\"}");
            }
            if(e.getMessage().equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
        }
    }

    public void logout(Context context) throws Exception{
        String authtoken=context.header("Authorization");
        sessionService.logout(authtoken);
        context.status(200);
    }
}
