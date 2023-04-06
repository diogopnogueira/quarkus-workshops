package com.example.movie.control;

import com.example.movie.control.exceptions.MovieException;
import com.example.movie.control.mapper.MovieMapper;
import com.example.movie.control.model.Movie;
import com.example.movie.entity.MovieRepository;
import com.example.movie.entity.domain.MovieEntity;
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
class MovieServiceTest {

    @Inject
    MovieService movieService;

    @InjectMock
    MovieRepository movieRepository;

    @InjectMock
    MovieMapper movieMapper;


    @Test
    void testCreateMovie() {
//      Given
        String id = "1";
        Movie movie = new Movie("", "asd", "asd", 333);
        MovieEntity movieEntity = new MovieEntity(null, "asd", "asd", 333);

//      When
        when(movieMapper.mapToEntity(movie)).thenReturn(movieEntity);
        when(movieRepository.persistMovie(movieEntity)).thenReturn(id);
        String movieId = assertDoesNotThrow(() -> movieService.createMovie(movie));

//      Then
        assertEquals(movieId, id);
    }

    @Test
    void testCreateMovieThrowException() {
//      Given
        Movie movie = new Movie("", "asd", "asd", 333);
        MovieEntity movieEntity = new MovieEntity(12L, "asd", "asd", 333);

//      When
        when(movieRepository.getMovieByName(anyString())).thenReturn(movieEntity);
        when(movieMapper.mapToBusiness(movieEntity)).thenReturn(movie);

//      Then
        assertThrows(MovieException.class, () -> movieService.createMovie(movie));
    }


    @Test
    void testGetMovieById() {
//      Given
        Movie movie = new Movie("33", "asd", "asd", 333);
        MovieEntity movieEntity = new MovieEntity(33L, "asd", "asd", 333);

//      When
        when(movieRepository.getMovieById(movie.getId())).thenReturn(movieEntity);
        when(movieMapper.mapToBusiness(movieEntity)).thenReturn(movie);
        Movie movieById = assertDoesNotThrow(() -> movieService.getMovieById(movie.getId()));

//      Then
        assertNotNull(movieById);
        assertEquals(movie.getId(), movieById.getId());
    }


    @Test
    void testGetMovieByIdThrowException() {
//      Given
        String movieId = "33";

//      When
        when(movieRepository.getMovieById(movieId)).thenReturn(null);

//      Then
        assertThrows(MovieException.class, () -> movieService.getMovieById(movieId));
    }

    @Test
    void testUpdateMovie() {
        //        Given
        Movie newMovie = new Movie("1", "asd", "asd", 333);
        MovieEntity movieEntity = new MovieEntity(1L, "bbb", "ccc", 312);
        MovieEntity newMovieEntity = new MovieEntity(1L, "asd", "asd", 333);

        //      When
        when(movieRepository.getMovieById(newMovie.getId())).thenReturn(movieEntity);
        when(movieMapper.mapToEntity(newMovie)).thenReturn(newMovieEntity);
        when(movieRepository.updateMovie(movieEntity, newMovieEntity)).thenReturn(newMovieEntity);
        when(movieMapper.mapToBusiness(newMovieEntity)).thenReturn(newMovie);
        Movie updatedMovie = assertDoesNotThrow(() -> movieService.updateMovie(newMovie));

        //      Then
        assertNotNull(updatedMovie);
        assertEquals(newMovie.getId(), updatedMovie.getId());
        assertEquals(newMovie.getName(), updatedMovie.getName());
        assertEquals(newMovie.getGenre(), updatedMovie.getGenre());
        assertEquals(newMovie.getDuration(), updatedMovie.getDuration());
    }

    @Test
    void testUpdateMovieThrowException() {
        //        Given
        Movie newMovie = new Movie("1", "asd", "asd", 333);

        //      When
        when(movieRepository.getMovieById(newMovie.getId())).thenThrow(MovieException.class);
        assertThrows(MovieException.class, () -> movieService.updateMovie(newMovie));
    }


    @Test
    void testDeleteMovie() {
        //      Given
        String id = "11";
        MovieEntity movie = new MovieEntity(Long.parseLong(id), "asd", "asd", 333);

        //      When
        when(movieRepository.getMovieById(id)).thenReturn(movie);
        assertDoesNotThrow(() -> movieService.deleteMovie(id));
    }


    @Test
    void testDeleteMovieThrowException() {
        //        Given
        String id = "1";

        //      When
        when(movieRepository.getMovieById(anyString())).thenThrow(MovieException.class);
        assertThrows(MovieException.class, () -> movieService.deleteMovie(id));
    }

    @Test
    void getMovieByDesignation() {
        //      Given
        String name = "asd";
        MovieEntity movie = new MovieEntity(11L, name, "asd", 333);

        //      When
        when(movieRepository.getMovieByName(name)).thenReturn(movie);
        assertDoesNotThrow(() -> movieService.getMovieByName(name));
    }

}