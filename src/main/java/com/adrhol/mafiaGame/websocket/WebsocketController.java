package com.adrhol.mafiaGame.websocket;

import com.adrhol.mafiaGame.config.PlayerPrincipal;
import com.adrhol.mafiaGame.model.GameStateObject;
import com.adrhol.mafiaGame.model.SimplePlayerDto;
import com.adrhol.mafiaGame.rooms.model.Player;
import com.adrhol.mafiaGame.rooms.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import java.security.Principal;

@Controller
@Slf4j
public class WebsocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpUserRegistry userRegistry;
    private final RoomService roomService;
    @Autowired
    public WebsocketController(final SimpMessagingTemplate simpMessagingTemplate,
                               final SimpUserRegistry userRegistry,
                               final RoomService roomService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userRegistry = userRegistry;
        this.roomService = roomService;
    }
    @EventListener
    public void logUserConnection(SessionConnectEvent event){
        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        log.info("Session Websocket Connected: " + header.getSessionId());
    }
    @SubscribeMapping("/room/{roomId}")
    public void sendNewPlayer(@DestinationVariable String roomId, StompHeaderAccessor header, Principal principalek){

        if(!isAdmin(roomId, header.getFirstNativeHeader("adminToken"))){
            PlayerPrincipal principal = (PlayerPrincipal) principalek;
            SimplePlayerDto player = new SimplePlayerDto(principal.getID().toString(), header.getFirstNativeHeader("login"));
            log.info("id " + principal.getID().toString());

            simpMessagingTemplate.convertAndSend(String.format("/topic/room/%s", roomId), player);
        }
    }
    @MessageMapping("/game-state-topic")
    public String sendSomeMessage(@Payload GameStateObject message){
        log.info("Sending game state to user id = {} and name {}", message.playerId(),message.playerName());
        simpMessagingTemplate.convertAndSendToUser(message.playerId(),"/queue/users", message);
        return message.playerId();
    }

    private boolean isAdmin(String roomId, String adminToken){
       return roomService.validateAdminToken(roomId, adminToken);
    }
}
