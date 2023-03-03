package com.example.room.repository;

import com.example.room.mapper.RoomDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RoomRepositoryTest {

    @Inject
    RoomRepository roomRepository;

    @Test
    void testPersistRoom() {
        //      Given
        RoomDTO roomDTO = new RoomDTO("11", "aaa", "aaa", 11, 11.00);

        //      When
        String persistedRoomId = roomRepository.persistRoom(roomDTO);

        //      Then
        assertEquals(roomDTO.getId(), persistedRoomId);
    }

    @Test
    void testGetRoomById() {
        //      Given
        String id = "11";

        //      When
        RoomDTO room= roomRepository.getRoomById(id);

        //      Then
        assertNotNull(room);
        assertEquals(id, room.getId());
    }


    @Test
    void testUpdateRoom() {
        //      Given
        RoomDTO roomDTO = new RoomDTO("11", "aaa", "aaa", 11, 11.00);

        //      When
        RoomDTO updatedRoom = roomRepository.updateRoom(roomDTO);

        //      Then
        assertNotNull(updatedRoom);
        assertEquals(roomDTO.getId(), updatedRoom.getId());
        assertEquals(roomDTO.getDesignation(), updatedRoom.getDesignation());
        assertEquals(roomDTO.getCapacity(), updatedRoom.getCapacity());
        assertEquals(roomDTO.getPrice(), updatedRoom.getPrice());
        assertEquals(roomDTO.getMovieName(), updatedRoom.getMovieName());
    }

    @Test
    void testDeleteRoom() {
        //      Given
        String id = "11";

        //      When
        boolean deleted = roomRepository.deleteRoom(id);

        //      Then
        assertTrue(deleted);
    }
}