package client;

import jakarta.websocket.*;

import java.net.URI;

public class WebSocketCommunicator extends Endpoint {
    public Session session;

    public WebSocketCommunicator() throws Exception{
        URI uri = new URI("ws://localhost:8080/ws");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        this.session=container.connectToServer(this,uri);
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig){
        System.out.println("connected");
    }
}
