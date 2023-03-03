package com.example.room.mapper;

import com.example.room.domain.Room;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RoomMapperTest {

    @Test
    void mapToEntity() {
        //      Given
        RoomDTO roomDTO = new RoomDTO("11","aaa", "aaa", 11, 11.00);

        //      When
        Room room = RoomMapper.mapToEntity(roomDTO);

        //      Then
        assertNotNull(room);
        assertEquals(roomDTO.getId(), String.valueOf(room.id));
        assertEquals(roomDTO.getDesignation(), room.designation);
        assertEquals(roomDTO.getCapacity(), room.capacity);
        assertEquals(roomDTO.getPrice(), room.price);
        assertEquals(roomDTO.getMovieName(), room.currentMovie);
    }

    @Test
    void mapToDTO() {
        //      Given
        Room room = new Room();
        room.id = Long.parseLong("33");
        room.designation = "asd";
        room.currentMovie = "asd";
        room.capacity = 33;
        room.price = 33;

        //      When
        RoomDTO roomDTO = RoomMapper.mapToDTO(room);

        //      Then
        assertNotNull(room);
        assertEquals(roomDTO.getId(), String.valueOf(room.id));
        assertEquals(roomDTO.getDesignation(), room.designation);
        assertEquals(roomDTO.getCapacity(), room.capacity);
        assertEquals(roomDTO.getPrice(), room.price);
        assertEquals(roomDTO.getMovieName(), room.currentMovie);
    }
}