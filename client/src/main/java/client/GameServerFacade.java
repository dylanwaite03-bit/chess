package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.UserData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameServerFacade {
    private final String server_url="http://localhost:8080";
    private final HttpClient client= HttpClient.newHttpClient();
    private final Gson gson=new Gson();


}
