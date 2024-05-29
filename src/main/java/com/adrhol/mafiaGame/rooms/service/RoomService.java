package com.adrhol.mafiaGame.rooms.service;

import com.adrhol.mafiaGame.rooms.model.Room;
import com.adrhol.mafiaGame.rooms.model.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class RoomService {

    private Map<String, Room> rooms;

    public RoomService() {
        this.rooms = new HashMap<>();
    }

    public Room createRoom(String roomName){
        String id = generateId();
        String adminToken = prepareAdminToken();

        Room room =  new Room(id, roomName, adminToken);
        this.rooms.put(id, room);

        return room;
    }
    private String generateId(){
        return UUID.randomUUID().toString().substring(0, 6);
    }
    private String prepareAdminToken(){
        return UUID.randomUUID().toString().substring(0,10);
    }

    public boolean validateAdminToken(final String roomId, final String adminToken) {
        log.info(roomId + " Received token: " + adminToken);
        log.info(roomId + " Found token: " + this.rooms.get(roomId).getAdminToken());
        return this.rooms.get(roomId).getAdminToken().equals(adminToken);
    }
}
