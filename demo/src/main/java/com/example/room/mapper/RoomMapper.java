package com.example.room.mapper;

import com.example.room.domain.Room;

public class RoomMapper {
    public static Room mapToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.id = Long.parseLong(roomDTO.getId());
        room.designation = roomDTO.getDesignation();
        room.currentMovie = roomDTO.getMovieName();
        room.capacity = roomDTO.getCapacity();
        room.price = roomDTO.getPrice();
        return room;
    }

    public static RoomDTO mapToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(String.valueOf(room.id));
        roomDTO.setDesignation(room.designation);
        roomDTO.setCapacity(room.capacity);
        roomDTO.setMovieName(room.currentMovie);
        roomDTO.setPrice(room.id);
        return roomDTO;
    }
}
