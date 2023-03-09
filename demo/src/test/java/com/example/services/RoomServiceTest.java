package com.example.services;

import com.example.room.repository.RoomRepository;
import com.example.room.resources.model.RoomDTO;
import com.example.room.services.RoomService;
import com.example.room.services.exceptions.RoomException;
import com.example.room.services.model.Room;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class RoomServiceTest {

    @Inject
    private RoomService roomService;

    @InjectMock
    private RoomRepository roomRepository;


    @Test
    void testCreateRoom() {
//      Given
        Room room = new Room("", "asd", "asd", 33, 33.0);

//      When
        when(roomRepository.persistRoom(Mockito.any(Room.class))).thenReturn("0");
        String roomId = assertDoesNotThrow(() -> roomService.createRoom(room));

//      Then
        assertEquals(roomId, "0");
    }

    @Test
    void testCreateRoomThrowException() {
//      Given
        Room room = new Room("", "asd", "asd", 33, 33.0);

//      When
        when(roomRepository.getRoomByDesignation(anyString())).thenReturn(room);

//      Then
        assertThrows(RoomException.class, () -> roomService.createRoom(room));
    }

    @Test
    void testGetRoomById() {
//      Given
        Room room = new Room("33", "asd", "asd", 33, 33.0);

//      When
        when(roomRepository.getRoomById(room.getId())).thenReturn(room);
        RoomDTO roomDTO = assertDoesNotThrow(() -> roomService.getRoomById(room.getId()));

//      Then
        assertNotNull(roomDTO);
        assertEquals(room.getId(), roomDTO.getId());
    }


    @Test
    void testGetRoomByIdThrowException() {
//      Given
        String roomId = "33";

//      When
        when(roomRepository.getRoomById(roomId)).thenReturn(null);

//      Then
        assertThrows(RoomException.class, () -> roomService.getRoomById(roomId));
    }

    @Test
    void testUpdateRoom() {
        //      Given
        Room roomToUpdate = new Room("1", "bbb", "ccc", 312, 312);
        Room newRoom = new Room("1", "asd", "asd", 33, 33.0);

        //      When
        when(roomRepository.getRoomById(roomToUpdate.getId())).thenReturn(roomToUpdate);
        when(roomRepository.updateRoom(roomToUpdate.getId(), newRoom)).thenReturn(newRoom);
        RoomDTO updatedRoom = assertDoesNotThrow(() -> roomService.updateRoom(roomToUpdate.getId(), newRoom));

        //      Then
        assertNotNull(updatedRoom);
        assertEquals(roomToUpdate.getId(), updatedRoom.getId());
        assertEquals(newRoom.getDesignation(), updatedRoom.getDesignation());
        assertEquals(newRoom.getCapacity(), updatedRoom.getCapacity());
        assertEquals(newRoom.getPrice(), updatedRoom.getPrice());
        assertEquals(newRoom.getCurrentMovie(), updatedRoom.getMovieName());
    }

    @Test
    void testDeleteRoom() {
        //      Given
        Room room = new Room("11", "asd", "asd", 33, 33);

        //      When
        when(roomRepository.getRoomById(room.getId())).thenReturn(room);
        assertDoesNotThrow(() -> roomService.deleteRoom(room.getId()));
    }

    @Test
    void getRoomByDesignation() {
        //      Given
        Room room = new Room("11", "asd", "asd", 33, 33);

        //      When
        when(roomRepository.getRoomByDesignation(room.getDesignation())).thenReturn(room);
        assertDoesNotThrow(() -> roomService.getRoomByDesignation(room.getDesignation()));
    }

}