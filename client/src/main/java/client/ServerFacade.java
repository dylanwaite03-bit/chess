package client;

import com.google.gson.Gson;
import model.UserData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerFacade {
    private final String server_url="http://localhost:8080";
    private final HttpClient client= HttpClient.newHttpClient();
    private final Gson gson=new Gson();

    public String register(String username,String password, String email) throws Exception{
        UserData registerRequest=new UserData(username,password,email);
        String json=gson.toJson(registerRequest);
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(server_url+"/user")).
                header("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(json)).build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
