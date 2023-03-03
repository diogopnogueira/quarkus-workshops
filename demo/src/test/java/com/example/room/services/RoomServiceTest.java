package com.example.room.services;

import com.example.room.mapper.RoomDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RoomServiceTest {

    @Inject
    private RoomService roomService;

    @Test
    void testCreateRoom() {
//      Given
        RoomDTO room = new RoomDTO("33", "asd", "asd", 33, 33.0);

//      When
        String id = roomService.createRoom(room);

//      Then
        assertEquals(id, room.getId());

    }

    @Test
    void testGetRoomById() {
//      Given
        String id = "33";

//      When
        RoomDTO room = roomService.getRoomById(id);

//      Then
        assertNotNull(room);
        assertEquals(id, room.getId());

    }

    @Test
    void testUpdateRoom() {
        //      Given
        RoomDTO roomDTO = new RoomDTO("11", "aaa", "aaa", 11, 11.00);

        //      When
        RoomDTO updatedRoom = roomService.updateRoom(roomDTO);

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
        boolean deleted = roomService.deleteRoom(id);

        //      Then
        assertTrue(deleted);
    }
}