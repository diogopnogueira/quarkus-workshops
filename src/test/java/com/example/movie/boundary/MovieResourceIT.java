package com.example.movie.boundary;

import com.example.movie.boundary.assembler.MovieAssembler;
import com.example.movie.boundary.model.MovieDTO;
import com.example.movie.boundary.model.UpdateMovieDTO;
import com.example.movie.control.MovieService;
import com.example.movie.control.exceptions.MovieException;
import com.example.movie.control.model.Movie;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@QuarkusTest
class MovieResourceIT {

    @InjectMock
    MovieService movieService;

    @InjectMock
    MovieAssembler movieAssembler;

    @Test
    void testCreateMovieEndpoint() {
        MovieDTO movieDto = new MovieDTO("name", "genre", 333);
        Movie movie = new Movie("1", "name", "genre", 333);

        when(movieAssembler.assembleMovieDtoToBusiness(movieDto)).thenReturn(movie);
        when(movieService.createMovie(movie)).thenReturn("1");

        given()
                .contentType("application/json")
                .body(movieDto)
                .when()
                .post("/movie")
                .then()
                .statusCode(201)
                .body(is("1"));
    }


    @Test
    public void testCreateEndpointWrongInput() {
        given()
                .contentType("application/json")
                .body(new MovieDTO("", "", "", 0))
                .when()
                .post("/movie")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateEndpointThrowMovieException() {
        MovieDTO movie = new MovieDTO("", "asd", "asdasd", 333);
        when(movieService.createMovie(movieAssembler.assembleMovieDtoToBusiness(movie))).thenThrow(MovieException.class);

        given()
                .contentType("application/json")
                .body(movie)
                .when()
                .post("/movie")
                .then()
                .statusCode(409);
    }

    @Test
    public void testGetEndpoint() {
        Movie movie = new Movie("1", "bbb", "ccc", 333);
        when(movieService.getMovieById("1")).thenReturn(movie);
        when(movieAssembler.assembleToDto(movie)).thenReturn(new MovieDTO("1", "bbb", "ccc", 333));

        given()
                .contentType("application/json")
                .pathParam("movieId", "1")
                .when()
                .get("/movie/{movieId}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("movieId", is("1"))
                .body("movieName", is("bbb"))
                .body("movieGenre", is("ccc"))
                .body("movieDuration", is(333));
    }

    @Test
    public void testGetEndpointThrowException() {
        when(movieService.getMovieById("1")).thenThrow(MovieException.class);

        given()
                .contentType("application/json")
                .pathParam("movieId", "1")
                .when()
                .get("/movie/{movieId}")
                .then()
                .statusCode(409);
    }


    @Test
    public void testUpdateEndpoint() {
        String movieId = "1";
        String movieName = "aaa";
        String movieGenre = "aaa";
        int movieDuration = 444;
        UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO(movieId, movieName, movieGenre, movieDuration);
        Movie movie = new Movie(movieId, movieName, movieGenre, movieDuration);
        MovieDTO movieDTO = new MovieDTO(movieId, movieName, movieGenre, movieDuration);

        when(movieAssembler.assembleUpdateMovieDtoToBusiness(updateMovieDTO)).thenReturn(movie);
        when(movieService.updateMovie(movie)).thenReturn(movie);
        when(movieAssembler.assembleToDto(movie)).thenReturn(movieDTO);

        given()
                .contentType("application/json")
                .body(updateMovieDTO)
                .when()
                .put("/movie")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("movieId", is(movieId))
                .body("movieName", is(movieName))
                .body("movieGenre", is(movieGenre))
                .body("movieDuration", is(movieDuration));
    }

    @Test
    public void testUpdateEndpointThrowException() {
        String movieId = "1";
        String movieName = "aaa";
        String movieGenre = "aaa";
        int movieDuration = 444;
        UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO(movieId, movieName, movieGenre, movieDuration);
        Movie newMovie = new Movie(movieId, movieName, movieGenre, movieDuration);

        when(movieAssembler.assembleUpdateMovieDtoToBusiness(updateMovieDTO)).thenReturn(newMovie);
        when(movieService.updateMovie(newMovie)).thenThrow(MovieException.class);

        given()
                .contentType("application/json")
                .body(updateMovieDTO)
                .when()
                .put("/movie")
                .then()
                .statusCode(409);
    }

    @Test
    public void testUpdateEndpointWrongInput() {
        given()
                .contentType("application/json")
                .body(new UpdateMovieDTO("", "", "", 0))
                .when()
                .put("/movie")
                .then()
                .statusCode(400);
    }


    @Test
    public void testDeleteEndpoint() {
        given()
                .contentType("application/json")
                .pathParam("movieId", "33")
                .when()
                .delete("/movie/{movieId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteEndpointThrowException() {
        doThrow(MovieException.class).when(movieService).deleteMovie(anyString());

        given()
                .contentType("application/json")
                .pathParam("movieId", "33")
                .when()
                .delete("/movie/{movieId}")
                .then()
                .statusCode(409);
    }

}
    
