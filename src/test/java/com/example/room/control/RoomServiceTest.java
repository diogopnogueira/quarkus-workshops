package com.example.room.control;

import com.example.room.entity.RoomRepository;
import com.example.room.entity.domain.RoomEntity;
import com.example.room.control.exceptions.RoomException;
import com.example.room.control.mapper.RoomMapper;
import com.example.room.control.model.Room;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class RoomServiceTest {

    @Inject
    RoomService roomService;

    @InjectMock
    RoomRepository roomRepository;

    @InjectMock
    RoomMapper roomMapper;

    @Test
    void testCreateRoom() {
//      Given
        String id = "1";
        Room room = new Room("", "asd", "asd", 33, 33.0);
        RoomEntity roomEntity = new RoomEntity(null, "asd", "asd", 33, 33.0);

//      When
        when(roomMapper.mapToEntity(room)).thenReturn(roomEntity);
        when(roomRepository.persistRoom(roomEntity)).thenReturn(id);
        String roomId = assertDoesNotThrow(() -> roomService.createRoom(room));

//      Then
        assertEquals(roomId, id);
    }

    @Test
    void testCreateRoomThrowException() {
//      Given
        Room room = new Room("", "asd", "asd", 33, 33.0);
        RoomEntity roomEntity = new RoomEntity(12L, "asd", "asd", 33, 33.0);

//      When
        when(roomRepository.getRoomByDesignation(anyString())).thenReturn(roomEntity);
        when(roomMapper.mapToBusiness(roomEntity)).thenReturn(room);

//      Then
        assertThrows(RoomException.class, () -> roomService.createRoom(room));
    }

    @Test
    void testGetRoomById() {
//      Given
        Room room = new Room("33", "asd", "asd", 33, 33.0);
        RoomEntity roomEntity = new RoomEntity(33L, "asd", "asd", 33, 33.0);

//      When
        when(roomRepository.getRoomById(room.getId())).thenReturn(roomEntity);
        when(roomMapper.mapToBusiness(roomEntity)).thenReturn(room);
        Room roomById = assertDoesNotThrow(() -> roomService.getRoomById(room.getId()));

//      Then
        assertNotNull(roomById);
        assertEquals(room.getId(), roomById.getId());
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
        //        Given
        Room newRoom = new Room("1", "asd", "asd", 33, 33);
        RoomEntity roomEntity = new RoomEntity(1L, "bbb", "ccc", 312, 312);
        RoomEntity newRoomEntity = new RoomEntity(1L, "asd", "asd", 33, 33);

        //      When
        when(roomRepository.getRoomById(newRoom.getId())).thenReturn(roomEntity);
        when(roomMapper.mapToEntity(newRoom)).thenReturn(newRoomEntity);
        when(roomRepository.updateRoom(roomEntity, newRoomEntity)).thenReturn(newRoomEntity);
        when(roomMapper.mapToBusiness(newRoomEntity)).thenReturn(newRoom);
        Room updatedRoom = assertDoesNotThrow(() -> roomService.updateRoom(newRoom));

        //      Then
        assertNotNull(updatedRoom);
        assertEquals(newRoom.getId(), updatedRoom.getId());
        assertEquals(newRoom.getDesignation(), updatedRoom.getDesignation());
        assertEquals(newRoom.getCapacity(), updatedRoom.getCapacity());
        assertEquals(newRoom.getPrice(), updatedRoom.getPrice());
        assertEquals(newRoom.getCurrentMovie(), updatedRoom.getCurrentMovie());
    }

    @Test
    void testUpdateRoomThrowException() {
        //        Given
        Room newRoom = new Room("1", "asd", "asd", 33, 33);

        //      When
        when(roomRepository.getRoomById(newRoom.getId())).thenThrow(RoomException.class);
        assertThrows(RoomException.class, () -> roomService.updateRoom(newRoom));
    }


    @Test
    void testDeleteRoom() {
        //      Given
        String id = "11";
        RoomEntity room = new RoomEntity(Long.parseLong(id), "asd", "asd", 33, 33);

        //      When
        when(roomRepository.getRoomById(id)).thenReturn(room);
        assertDoesNotThrow(() -> roomService.deleteRoom(id));
    }


    @Test
    void testDeleteRoomThrowException() {
        //        Given
        String id = "1";

        //      When
        when(roomRepository.getRoomById(anyString())).thenThrow(RoomException.class);
        assertThrows(RoomException.class, () -> roomService.deleteRoom(id));
    }

    @Test
    void getRoomByDesignation() {
        //      Given
        String designation = "asd";
        RoomEntity room = new RoomEntity(11L, designation, "asd", 33, 33);

        //      When
        when(roomRepository.getRoomByDesignation(designation)).thenReturn(room);
        assertDoesNotThrow(() -> roomService.getRoomByDesignation(designation));
    }


}