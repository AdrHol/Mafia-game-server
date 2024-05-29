package com.adrhol.mafiaGame.rooms.mappers;

import com.adrhol.mafiaGame.rooms.model.Room;
import com.adrhol.mafiaGame.rooms.model.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDto toSimpleDto(Room room){
        return new RoomDto(room.getId(), room.getName(), room.getAdminToken());
    }
}
