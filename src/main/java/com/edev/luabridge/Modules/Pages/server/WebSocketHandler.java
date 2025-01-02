package com.edev.luabridge.Modules.Pages.server;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Mensagem recebida: " + message.getPayload());

        try {
            session.sendMessage(new TextMessage("Resposta do servidor: " + message.getPayload()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
