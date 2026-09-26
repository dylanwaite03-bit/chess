package handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import service.RegisterRequest;
import service.RegisterResult;
import service.UserService;

public class UserHandler {
    private final UserService userservice;
    private final Gson gson=new Gson();

    public UserHandler(UserService userservice){
        this.userservice=userservice;
    }

    public void register(Context context) throws Exception{
        RegisterRequest request= gson.fromJson(context.body(),RegisterRequest.class);
        RegisterResult result=userservice.register(request);
        context.json(result);
    }

}
