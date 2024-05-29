package com.adrhol.mafiaGame.rooms.controller;

import com.adrhol.mafiaGame.rooms.mappers.RoomMapper;
import com.adrhol.mafiaGame.rooms.model.CreateRoomRequest;
import com.adrhol.mafiaGame.rooms.model.CreateRoomResponse;
import com.adrhol.mafiaGame.rooms.model.OpenResponse;
import com.adrhol.mafiaGame.rooms.model.RoomDto;
import com.adrhol.mafiaGame.rooms.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController {

    private final RoomService service;
    private final RoomMapper roomMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public RoomController(final RoomService service, final RoomMapper roomMapper, final RestTemplate restTemplate) {
        this.service = service;
        this.roomMapper = roomMapper;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest requestBody){
        RoomDto roomDto = this.roomMapper.toSimpleDto(this.service.createRoom(requestBody.getName()));

        return ResponseEntity.ok(new CreateRoomResponse(roomDto.id(), roomDto.name(), roomDto.adminToken()));
    }

}
