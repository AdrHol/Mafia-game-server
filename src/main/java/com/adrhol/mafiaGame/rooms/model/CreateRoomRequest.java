package com.adrhol.mafiaGame.rooms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequest {
    private Procedure procedure;
    private String name;

    public CreateRoomRequest(@JsonProperty("procedure") Procedure procedure,
                             @JsonProperty("name") String name){
        this.procedure = procedure;
        this.name = name;
    }
}