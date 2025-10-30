package socket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{username}")
public class ChatServer {
    
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
        clients.put(username, session);
        broadcastMessage(username + " 加入了聊天室！");
    }
    
    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) throws IOException {
        broadcastMessage(username + ": " + message);
    }
    
    @OnClose
    public void onClose(@PathParam("username") String username) throws IOException {
        clients.remove(username);
        broadcastMessage(username + " 离开了聊天室！");
    }
    
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    
    private void broadcastMessage(String message) throws IOException {
        for (Session session : clients.values()) {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        }
    }
}
