package com.example.room.repository;

import com.example.room.mapper.RoomDTO;
import com.example.room.domain.Room;
import com.example.room.mapper.RoomMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository {


    public String persistRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.mapToEntity(roomDTO);
        return String.valueOf(room.id);
    }

    public RoomDTO getRoomById(String roomId) {
        Room room = new Room("asd", "asd", 2, 33);
        room.id = Long.parseLong(roomId);
        return RoomMapper.mapToDTO(room);

    }


    public RoomDTO updateRoom(RoomDTO roomDTO) {
        return new RoomDTO(roomDTO.getId(), roomDTO.getDesignation(), roomDTO.getMovieName(), roomDTO.getCapacity(),
                roomDTO.getPrice());
    }

    public boolean deleteRoom(String roomId) {
        return true;
    }
}
