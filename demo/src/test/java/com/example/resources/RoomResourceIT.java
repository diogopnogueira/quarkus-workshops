package com.example.resources;

import com.example.room.resources.assembler.RoomAssembler;
import com.example.room.resources.model.RoomDTO;
import com.example.room.resources.model.UpdateRoomDTO;
import com.example.room.services.RoomService;
import com.example.room.services.exceptions.RoomException;
import com.example.room.services.model.Room;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class RoomResourceIT {

    @InjectMock
    RoomService roomService;

    @InjectMock
    RoomAssembler roomAssembler;

    @Test
    public void testCreateEndpoint() {
        RoomDTO roomDTO = new RoomDTO("asd", "asd", 33, 33);
        Room room = new Room("", "asd", "asd", 33, 33);
        when(roomAssembler.assembleRoomDtoToBusiness(roomDTO)).thenReturn(room);
        when(roomService.createRoom(room)).thenReturn("0");

        given()
                .contentType("application/json")
                .body(roomDTO)
                .when()
                .post("/room")
                .then()
                .statusCode(201)
                .body(is("0"));
    }

    @Test
    public void testCreateEndpointWrongInput() {
        given()
                .contentType("application/json")
                .body(new RoomDTO("", "", 0, 0))
                .when()
                .post("/room")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateEndpointThrowException() {
        RoomDTO room = new RoomDTO("bbb", "asd", 33, 33);
        when(roomService.createRoom(roomAssembler.assembleRoomDtoToBusiness(room))).thenThrow(RoomException.class);

        given()
                .contentType("application/json")
                .body(room)
                .when()
                .post("/room")
                .then()
                .statusCode(409);
    }

    @Test
    public void testGetEndpoint() {
        Room room = new Room("1", "bbb", "ccc", 312, 312);
        when(roomService.getRoomById("1")).thenReturn(room);
        when(roomAssembler.assembleToDto(room)).thenReturn(new RoomDTO("1", "bbb", "ccc", 312, 312));

        given()
                .contentType("application/json")
                .pathParam("roomId", "1")
                .when()
                .get("/room/{roomId}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is("1"))
                .body("designation", is("bbb"))
                .body("movieName", is("ccc"))
                .body("capacity", is(312))
                .body("price", is(312f));
    }

    @Test
    public void testGetEndpointThrowException() {
        when(roomService.getRoomById("1")).thenThrow(RoomException.class);

        given()
                .contentType("application/json")
                .pathParam("roomId", "1")
                .when()
                .get("/room/{roomId}")
                .then()
                .statusCode(409);
    }


    @Test
    public void testUpdateEndpoint() {
        String roomId = "1";
        UpdateRoomDTO updateRoomDTO = new UpdateRoomDTO(roomId, "asd", "asd", 33, 33.0);
        Room newRoom = new Room(roomId, "asd", "asd", 33, 33.0);
        RoomDTO roomDTO = new RoomDTO(roomId, "asd", "asd", 33, 33.0);

        when(roomAssembler.assembleUpdateRoomDtoToBusiness(updateRoomDTO)).thenReturn(newRoom);
        when(roomService.updateRoom(newRoom)).thenReturn(newRoom);
        when(roomAssembler.assembleToDto(newRoom)).thenReturn(roomDTO);

        given()
                .contentType("application/json")
                .body(updateRoomDTO)
                .when()
                .put("/room")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is("1"))
                .body("designation", is("asd"))
                .body("movieName", is("asd"))
                .body("capacity", is(33))
                .body("price", is(33.0f));
    }

    @Test
    public void testUpdateEndpointWrongInput() {
        given()
                .contentType("application/json")
                .body(new UpdateRoomDTO("", "", "", 0, 0))
                .when()
                .put("/room")
                .then()
                .statusCode(400);
    }


    @Test
    public void testDeleteEndpoint() {
        given()
                .contentType("application/json")
                .pathParam("roomId", "33")
                .when()
                .delete("/room/{roomId}")
                .then()
                .statusCode(200);
    }


}