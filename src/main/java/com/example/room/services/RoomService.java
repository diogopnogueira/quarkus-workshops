package com.example.room.services;

import com.example.room.repository.RoomRepository;
import com.example.room.repository.domain.RoomEntity;
import com.example.room.services.exceptions.RoomException;
import com.example.room.services.mapper.RoomMapper;
import com.example.room.services.model.Room;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;

@ApplicationScoped
@Transactional
@AllArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public String createRoom(final Room newRoom) {
        final Room room = getRoomByDesignation(newRoom.getDesignation());
        if (room != null) {
            log.info("Room Exception: " + newRoom.getDesignation() + " already exists!");
            throw new RoomException("Room with designation " + newRoom.getDesignation() + " already exists!");
        }

        RoomEntity roomEntity = roomMapper.mapToEntity(newRoom);
        String roomId = roomRepository.persistRoom(roomEntity);
        log.info("Persist Room: " + roomId + " persisted successfully");
        return roomId;
    }

    @Transactional(NOT_SUPPORTED)
    public Room getRoomByDesignation(final String roomDesignation) {
        RoomEntity roomByDesignation = roomRepository.getRoomByDesignation(roomDesignation);

        log.info("Get Room by Designation: " + roomDesignation);
        return roomByDesignation != null
                ? roomMapper.mapToBusiness(roomByDesignation)
                : null;
    }

    @Transactional(NOT_SUPPORTED)
    public Room getRoomById(final String roomId) {
        RoomEntity roomById = findRoomById(roomId);

        return roomMapper.mapToBusiness(roomById);
    }


    public Room updateRoom(final Room room) {
        final RoomEntity roomEntity = findRoomById(room.getId());
        final RoomEntity newRoomEntity = roomMapper.mapToEntity(room);
        final RoomEntity updatedRoomEntity = roomRepository.updateRoom(roomEntity, newRoomEntity);

        log.info("Update Room: " + updatedRoomEntity.id + " successfully!");
        return roomMapper.mapToBusiness(updatedRoomEntity);
    }

    public void deleteRoom(final String roomId) {
        final RoomEntity room = findRoomById(roomId);

        roomRepository.deleteRoom(room);
        log.info("Delete Room: " + roomId);
    }

    private RoomEntity findRoomById(final String roomId) {
        final RoomEntity roomEntity = roomRepository.getRoomById(roomId);
        if (roomEntity == null) {
            log.info("Room with id " + roomId + " not found");
            throw new RoomException("Room with id " + roomId + " not found");
        }

        log.info("Get Room by Id: " + roomId);
        return roomEntity;
    }

}
