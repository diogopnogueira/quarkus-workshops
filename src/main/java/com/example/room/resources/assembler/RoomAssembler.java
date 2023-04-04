package com.example.room.resources.assembler;

import com.example.room.resources.model.RoomDTO;
import com.example.room.resources.model.UpdateRoomDTO;
import com.example.room.services.model.Room;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomAssembler {


    public Room assembleRoomDtoToBusiness(RoomDTO roomDTO) {
        return new Room(roomDTO.getId(), roomDTO.getDesignation(), roomDTO.getMovieName(), roomDTO.getCapacity(), roomDTO.getPrice());
    }

    public Room assembleUpdateRoomDtoToBusiness(UpdateRoomDTO updateRoomDTO) {
        return new Room(updateRoomDTO.getId(), updateRoomDTO.getDesignation(), updateRoomDTO.getMovieName(), updateRoomDTO.getCapacity(), updateRoomDTO.getPrice());
    }

    public RoomDTO assembleToDto(Room room) {
        return new RoomDTO(room.getId(), room.getDesignation(), room.getCurrentMovie(), room.getCapacity(), room.getPrice());
    }
}
