package com.example.room.boundary.assembler;

import com.example.room.boundary.model.RoomDTO;
import com.example.room.boundary.model.UpdateRoomDTO;
import com.example.room.control.model.Room;

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
