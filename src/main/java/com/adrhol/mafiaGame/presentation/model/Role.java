package com.adrhol.mafiaGame.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Role {

    private int id;
    private String name;
    private String description;

    public Role(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
