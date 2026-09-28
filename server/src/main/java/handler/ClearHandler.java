package handler;

import io.javalin.http.Context;
import service.ClearService;

public class ClearHandler {
    private final ClearService clearService;

    public ClearHandler(ClearService clearService){
        this.clearService=clearService;
    }

    public void clear(Context context) throws Exception{
        clearService.clear();
        context.status(200).json("{}");
    }
}
