package client;

import java.net.http.HttpClient;

public class ServerFacade {
    private final String server_url="http://localhost:8080";
    private final HttpClient client= HttpClient.newHttpClient();



}
