package client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.GameData;
import model.UserData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerFacade {
    private final String serverUrl;
    private final HttpClient client= HttpClient.newHttpClient();
    private final Gson gson=new Gson();
    private String authToken=null;
    private String username=null;

    public ServerFacade(int port){
        this.serverUrl="http://localhost:"+port;
    }

    public boolean authenticated(){
        return authToken!=null && !authToken.isEmpty();
    }

    public String register(String username,String password, String email) throws Exception{
        UserData registerRequest=new UserData(username,password,email);
        String json=gson.toJson(registerRequest);
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/user")).
                header("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(json)).build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()!=200){
            throw new Exception("register failed, username may be taken");
        }
        JsonObject jsonLoad=gson.fromJson(response.body(),JsonObject.class);
        this.authToken=jsonLoad.get("authToken").getAsString();
        this.username=jsonLoad.get("username").getAsString();
        return response.body();
    }

    public String login (String username,String password) throws Exception{
        LoginRquest loginrequest=new LoginRquest(username,password);
        String json=gson.toJson(loginrequest);
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/session")).
                header("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(json)).build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()!=200){
            throw new Exception("login failed, incorrect username or password");
        }
        JsonObject jsonLoad=gson.fromJson(response.body(),JsonObject.class);
        this.authToken=jsonLoad.get("authToken").getAsString();
        this.username=jsonLoad.get("username").getAsString();
        return response.body();
    }

    private record LoginRquest(String username,String password){}

    public String logout () throws Exception{

        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/session")).
                header("Authorization",authToken).
                DELETE().build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        this.authToken=null;
        this.username=null;
        return response.body();
    }

    private record CreateGameRequest(String gameName,String authToken){}

    public String createGame(String gameName) throws Exception{
        CreateGameRequest gameRequest=new CreateGameRequest(gameName,authToken);
        String json=gson.toJson(gameRequest);
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/game")).
                header("Content-Type","application/json").
                header("Authorization",authToken).
                POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Map<Integer, GameData> listGames() throws Exception{

        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/game")).
                header("Content-Type","application/json").
                header("Authorization",authToken).
                GET().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        JsonObject json= gson.fromJson(response.body(),JsonObject.class);
        JsonArray games=json.getAsJsonArray("games");
        Map<Integer,GameData> gameMap=new LinkedHashMap<>();

        for (JsonElement element:games){
            GameData gameData=gson.fromJson(element,GameData.class);
            gameMap.put(gameData.gameID(), gameData);
        }
        return gameMap;
    }

    public record JoinGameRequest(int gameID, String playerColor, String authToken) {
    }

    public String joinGame(int gameID, String playerColor) throws Exception{
        JoinGameRequest registerRequest=new JoinGameRequest(gameID,playerColor,authToken);
        String json=gson.toJson(registerRequest);
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/game")).
                header("Content-Type","application/json").
                header("Authorization",authToken).
                PUT(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String clearDb() throws Exception{
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(serverUrl +"/db")).
                DELETE().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
