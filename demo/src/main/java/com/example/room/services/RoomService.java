package com.example.room.services;

import com.example.room.repository.RoomRepository;
import com.example.room.resources.assembler.RoomAssembler;
import com.example.room.resources.model.RoomDTO;
import com.example.room.services.exceptions.RoomException;
import com.example.room.services.model.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class RoomService {

    private static final Logger log = Logger.getLogger(RoomService.class);

    @Inject
    RoomRepository roomRepository;

    @Transactional
    public String createRoom(final Room newRoom) {
        final Room room = getRoomByDesignation(newRoom.getDesignation());
        if (room != null) {
            log.info("Room Exception: " + newRoom.getDesignation() + " already exists!");
            throw new RoomException("Room with designation " + newRoom.getDesignation() + " already exists!");
        }

        log.info("Persist Room: " + newRoom.getDesignation());
        return roomRepository.persistRoom(newRoom);
    }

    public Room getRoomByDesignation(final String roomDesignation) {
        log.info("Get Room by Designation: " + roomDesignation);
        return roomRepository.getRoomByDesignation(roomDesignation);
    }

    public RoomDTO getRoomById(final String roomId) {
        final Room room = getRoom(roomId);
        return RoomAssembler.assembleToDTO(room);
    }

    private Room getRoom(final String roomId) {
        final Room room = roomRepository.getRoomById(roomId);
        if (room == null) {
            log.info("Room with id " + roomId + " not found");
            throw new RoomException("Room with id " + roomId + " not found");
        }
        log.info("Get Room by Id: " + roomId);
        return room;
    }

    public RoomDTO updateRoom(final String roomId, final Room newRoom) {
        final String roomIdToUpdate = getRoom(roomId).getId();

        log.info("Update Room: " + roomId);
        final Room room = roomRepository.updateRoom(roomIdToUpdate, newRoom);
        return RoomAssembler.assembleToDTO(room);
    }

    public void deleteRoom(final String roomId) {
        final Room room = RoomAssembler.assembleToBusiness(getRoomById(roomId));

        log.info("Delete Room: " + roomId);
        roomRepository.deleteRoom(room.getId());
    }

}
