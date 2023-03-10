package com.example.room.repository;

import com.example.room.repository.domain.RoomEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomEntity> {

    private static final Logger log = Logger.getLogger(RoomRepository.class);

    public String persistRoom(RoomEntity roomEntity) {
        roomEntity.persistAndFlush();

        log.info("Persist: " + roomEntity.id + " successful");
        return String.valueOf(roomEntity.id);
    }

    public RoomEntity getRoomById(String roomId) {
        return findById(Long.valueOf(roomId));
    }


    public RoomEntity updateRoom(RoomEntity roomEntity, RoomEntity newRoomEntity) {
        roomEntity.designation = newRoomEntity.designation;
        roomEntity.currentMovie = newRoomEntity.currentMovie;
        roomEntity.capacity = newRoomEntity.capacity;
        roomEntity.price = newRoomEntity.price;
        roomEntity.persistAndFlush();

        return roomEntity;
    }

    public void deleteRoom(RoomEntity room) {
        deleteById(room.id);
    }

    public RoomEntity getRoomByDesignation(String roomDesignation) {
        return RoomEntity.find("designation", roomDesignation).firstResult();
    }

}
