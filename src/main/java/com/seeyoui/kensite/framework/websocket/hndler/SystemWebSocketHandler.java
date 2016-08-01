package com.seeyoui.kensite.framework.websocket.hndler;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import com.seeyoui.kensite.common.constants.StringConstant;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {

    private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<>();
    }
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        String userName = (String) session.getAttributes().get(StringConstant.WEBSOCKET_USERNAME);
        if(userName!= null){
            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
//            session.sendMessage(new TextMessage(count + ""));
        }
    }
 
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	TextMessage textMessage = new TextMessage(message.getPayload().toString());
    	sendMessageToUsers(textMessage);
    }
 
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(StringConstant.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}