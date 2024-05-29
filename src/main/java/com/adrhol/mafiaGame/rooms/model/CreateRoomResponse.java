package com.adrhol.mafiaGame.rooms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomResponse {

    private String id;
    private String name;
    private String adminToken;
    public CreateRoomResponse(final String id, final String name, final String adminToken) {
        this.id = id;
        this.name = name;
        this.adminToken = adminToken;
    }

}
