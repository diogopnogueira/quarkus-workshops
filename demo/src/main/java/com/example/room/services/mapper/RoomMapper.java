package com.example.room.services.mapper;

import com.example.room.repository.domain.RoomEntity;
import com.example.room.services.model.Room;

public class RoomMapper {

    private RoomMapper() {
    }

    public static RoomEntity mapToEntity(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.designation = room.getDesignation();
        roomEntity.currentMovie = room.getCurrentMovie();
        roomEntity.capacity = room.getCapacity();
        roomEntity.price = room.getPrice();

        return roomEntity;
    }

    public static Room mapToBusiness(RoomEntity roomEntity) {
        return roomEntity != null ?
                new Room(String.valueOf(roomEntity.id), roomEntity.designation, roomEntity.currentMovie, roomEntity.capacity, roomEntity.price)
                : null;
    }
}
