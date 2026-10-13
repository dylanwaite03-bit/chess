package handler;

import io.javalin.http.Context;
import service.ClearService;

public class ClearHandler {
    private final ClearService clearService;

    public ClearHandler(ClearService clearService){
        this.clearService=clearService;
    }

    public void clear(Context context) throws Exception{
        try {
            clearService.clear();
            context.status(200).json("{}");
        }catch(Exception e){
            context.status(500);
            context.json("{\"message\":\"Error:" +e.getMessage() +"\"}");
        }
    }
}
