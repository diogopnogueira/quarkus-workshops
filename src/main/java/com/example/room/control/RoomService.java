package com.example.room.control;

import com.example.room.control.exceptions.RoomException;
import com.example.room.control.mapper.RoomMapper;
import com.example.room.control.model.Room;
import com.example.room.entity.RoomRepository;
import com.example.room.entity.domain.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
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
        try {
            RoomEntity roomByDesignation = roomRepository.getRoomByDesignation(roomDesignation);
            log.info("Get Room by Designation: " + roomDesignation);

            return roomMapper.mapToBusiness(roomByDesignation);
        } catch (NoResultException e) {
            log.info("Room with designation " + roomDesignation + " not found");

            return null;
        }
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

        log.info("Update Room: " + updatedRoomEntity.getId() + " successfully!");
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
