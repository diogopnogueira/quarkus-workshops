package com.example.room.repository;

import com.example.room.repository.domain.RoomEntity;
import com.example.room.services.mapper.RoomMapper;
import com.example.room.services.model.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomEntity> {

    private List<RoomEntity> rooms = new ArrayList<>();

    private static final Logger log = Logger.getLogger(RoomRepository.class);

    public String persistRoom(Room room) {
        RoomEntity roomEntity = RoomMapper.mapToEntity(room);
        roomEntity.persistAndFlush();

        log.info("Persist: " + roomEntity.id + " successful");
        return String.valueOf(roomEntity.id);
    }

    public Room getRoomById(String roomId) {
        return RoomMapper.mapToBusiness(findById(Long.valueOf(roomId)));
    }


    public Room updateRoom(String roomIdToUpdate, Room newRoom) {
        RoomEntity roomEntity = findById(Long.parseLong(roomIdToUpdate));
        roomEntity.designation = newRoom.getDesignation();
        roomEntity.currentMovie = newRoom.getCurrentMovie();
        roomEntity.capacity = newRoom.getCapacity();
        roomEntity.price = newRoom.getPrice();
        roomEntity.persistAndFlush();

        return RoomMapper.mapToBusiness(roomEntity);
    }

    public void deleteRoom(String roomId) {
        deleteById(Long.valueOf(roomId));
    }

    public Room getRoomByDesignation(String roomDesignation) {
        return RoomMapper.mapToBusiness(RoomEntity.find("designation", roomDesignation).firstResult());
    }

}
