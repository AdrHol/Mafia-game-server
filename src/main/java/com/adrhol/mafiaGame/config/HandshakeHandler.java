package com.adrhol.mafiaGame.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class HandshakeHandler extends DefaultHandshakeHandler implements HandshakeInterceptor {

    @Override
    protected Principal determineUser(final ServerHttpRequest request,
                                      final WebSocketHandler wsHandler,
                                      final Map<String, Object> attributes) {
        return new PlayerPrincipal(UUID.randomUUID(), "Test name");
    }

    @Override
    public boolean beforeHandshake(final ServerHttpRequest request,
                                   final ServerHttpResponse response,
                                   final WebSocketHandler wsHandler,
                                   final Map<String, Object> attributes) throws Exception {
        if(request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverRequest.getServletRequest()
                                               .getSession();
            attributes.put("sessionId", session.getId());
        }
        return false;
    }

    @Override
    public void afterHandshake(final ServerHttpRequest request,
                               final ServerHttpResponse response,
                               final WebSocketHandler wsHandler,
                               final Exception exception) {

    }

}
