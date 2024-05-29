package com.adrhol.mafiaGame.config;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.UUID;


public class PlayerPrincipal implements Principal {

    private UUID id;
    private String name;

    public PlayerPrincipal(UUID id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public String getName() {
        return this.id.toString();
    }
    public UUID getID(){
        return this.id;
    }
}
