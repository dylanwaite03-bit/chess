package handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import service.LoginRequest;
import service.LoginResult;
import service.SessionService;

import java.util.Map;

public class SessionHandler {
    private final SessionService sessionService;
    private final Gson gson=new Gson();

    public SessionHandler(SessionService sessionService) {
        this.sessionService=sessionService;
    }

    public void login(Context context) {
        LoginRequest request= gson.fromJson(context.body(), LoginRequest.class);
        try{
        LoginResult result=sessionService.login(request);
        context.json(result);
        } catch (Exception e){
            String message=e.getMessage();
            if(message.equals("bad request")){
                context.status(400);
                context.json("{\"message\":\"Error: bad request\"}");
            }
            else if(message.equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
            else{
                context.status(500);
                context.json("{\"message\":\"Error:" +e.getMessage() +"\"}");
            }
        }
    }

    public void logout(Context context){
        String authtoken=context.header("Authorization");
        try {
            sessionService.logout(authtoken);
            context.status(200);
        }catch (Exception e){
            if(e.getMessage().equals("unauthorized")){
                context.status(401);
                context.json("{\"message\":\"Error: unauthorized\"}");
            }
            else{
                context.status(500);
                context.json("{\"message\":\"Error:" +e.getMessage() +"\"}");
            }
        }
    }
}
