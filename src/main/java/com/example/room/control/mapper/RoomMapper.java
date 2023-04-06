package com.example.room.control.mapper;

import com.example.room.control.model.Room;
import com.example.room.entity.domain.RoomEntity;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RoomMapper {


    public RoomEntity mapToEntity(Room room) {
        return new RoomEntity(room.getDesignation(),
                room.getCurrentMovie(),
                room.getCapacity(),
                room.getPrice());
    }

    public Room mapToBusiness(RoomEntity roomEntity) {
        return new Room(String.valueOf(roomEntity.getId()),
                roomEntity.getDesignation(),
                roomEntity.getCurrentMovie(),
                roomEntity.getCapacity(),
                roomEntity.getPrice());
    }
}
