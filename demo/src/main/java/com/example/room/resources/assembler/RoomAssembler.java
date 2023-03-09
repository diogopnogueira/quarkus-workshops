package com.example.room.resources.assembler;

import com.example.room.resources.model.RoomDTO;
import com.example.room.services.model.Room;

public class RoomAssembler {

    private RoomAssembler() {
    }

    public static Room assembleToBusiness(RoomDTO newRoom) {
        return new Room(newRoom.getId(), newRoom.getDesignation(), newRoom.getMovieName(), newRoom.getCapacity(), newRoom.getPrice());
    }

    public static RoomDTO assembleToDTO(Room room) {
        return new RoomDTO(room.getId(), room.getDesignation(), room.getCurrentMovie(), room.getCapacity(), room.getPrice());
    }
}
