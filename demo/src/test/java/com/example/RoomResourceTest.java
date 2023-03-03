package com.example;

import com.example.room.mapper.RoomDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RoomResourceTest {

    @Test
    public void testCreateEndpoint() {
        given()
                .contentType("application/json")
                .body(new RoomDTO("33", "asd", "asd", 33, 33.0))
                .when().post("/room")
                .then()
                .statusCode(201)
                .body(is("33"));
    }

    @Test
    public void testGetEndpoint() {
        given()
                .contentType("application/json")
                .pathParam("id", 33)
                .when()
                .get("/room/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is("33"))
                .body("designation", is("asd"))
                .body("movieName", is("asd"))
                .body("capacity", is(2))
                .body("price", is(33.0f));
    }

    @Test
    public void testUpdateEndpoint() {
        given()
                .pathParam("id", 33)
                .contentType("application/json")
                .body(new RoomDTO("asd", "asd", 33, 33.0))
                .when()
                .put("/room/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is("33"))
                .body("designation", is("asd"))
                .body("movieName", is("asd"))
                .body("capacity", is(33))
                .body("price", is(33.0f));
    }

    @Test
    public void testDeleteEndpoint() {
        given()
                .contentType("application/json")
                .pathParam("id", 33)
                .when()
                .delete("/room/{id}")
                .then()
                .statusCode(200);
    }


}