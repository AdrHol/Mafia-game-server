package com.adrhol.mafiaGame.rooms.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Room {
    private String id;
    private String name;
    private String adminToken;
    private Player host;
    private List<Player> players;

    public Room(final String id, final String name, final String adminToken) {
        this.id = id;
        this.name = name;
        this.adminToken = adminToken;
        this.players = new ArrayList<>();
    }

}
