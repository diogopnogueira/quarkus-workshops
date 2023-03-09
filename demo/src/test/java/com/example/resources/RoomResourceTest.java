package com.example.resources;

import com.example.room.resources.assembler.RoomAssembler;
import com.example.room.resources.model.RoomDTO;
import com.example.room.services.RoomService;
import com.example.room.services.exceptions.RoomException;
import com.example.room.services.model.Room;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class RoomResourceTest {

    @InjectMock
    RoomService roomService;

    @Test
    public void testCreateEndpoint() {
        when(roomService.createRoom(Mockito.any(Room.class))).thenReturn("0");

        RoomDTO room = new RoomDTO("asd", "asd", 33, 33);
        given()
                .contentType("application/json")
                .body(room)
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
        when(roomService.getRoomByDesignation(room.getDesignation())).thenReturn(null);
        when(roomService.createRoom(RoomAssembler.assembleToBusiness(room))).thenThrow(RoomException.class);

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
        when(roomService.getRoomById("1"))
                .thenReturn(new RoomDTO("1", "bbb", "ccc", 312, 312));

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
        RoomDTO roomToUpdate = new RoomDTO(roomId, "bbb", "ccc", 312, 312);
        Room newRoom = new Room(roomId, "asd", "asd", 33, 33.0);

        when(roomService.getRoomById(roomId)).thenReturn(roomToUpdate);
        when(roomService.updateRoom(roomId, newRoom)).thenReturn(RoomAssembler.assembleToDTO(newRoom));

        given()
                .pathParam("roomId", 1)
                .contentType("application/json")
                .body(new RoomDTO(roomId, "asd", "asd", 33, 33.0))
                .when()
                .put("/room/{roomId}")
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
                .pathParam("roomId", 33)
                .contentType("application/json")
                .body(new RoomDTO("", "", 0, 0))
                .when()
                .put("/room/{roomId}")
                .then()
                .statusCode(400);
    }


    @Test
    public void testDeleteEndpoint() {
        when(roomService.getRoomById("33"))
                .thenReturn(new RoomDTO("33", "asd", "asd", 33, 33));

        given()
                .contentType("application/json")
                .pathParam("roomId", "33")
                .when()
                .delete("/room/{roomId}")
                .then()
                .statusCode(200);
    }


}