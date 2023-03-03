package com.example.room.services;

import com.example.room.mapper.RoomDTO;
import com.example.room.repository.RoomRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    public String createRoom(RoomDTO roomDTO) {
        return roomRepository.persistRoom(roomDTO);
    }

    public RoomDTO getRoomById(String roomId) {
        return roomRepository.getRoomById(roomId);
    }

    public RoomDTO updateRoom(RoomDTO roomDTO) {
        return roomRepository.updateRoom(roomDTO);
    }

    public boolean deleteRoom(String roomId) {
        return roomRepository.deleteRoom(roomId);
    }
}
