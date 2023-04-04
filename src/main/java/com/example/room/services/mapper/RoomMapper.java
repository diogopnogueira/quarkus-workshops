package com.example.room.services.mapper;

import com.example.room.repository.domain.RoomEntity;
import com.example.room.services.model.Room;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RoomMapper {


    public RoomEntity mapToEntity(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.designation = room.getDesignation();
        roomEntity.currentMovie = room.getCurrentMovie();
        roomEntity.capacity = room.getCapacity();
        roomEntity.price = room.getPrice();

        return roomEntity;
    }

    public Room mapToBusiness(RoomEntity roomEntity) {
        return new Room(String.valueOf(roomEntity.id), roomEntity.designation, roomEntity.currentMovie, roomEntity.capacity, roomEntity.price);
    }
}
