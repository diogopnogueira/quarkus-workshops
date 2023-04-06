package com.example.movie.control;

import com.example.movie.entity.MovieRepository;
import com.example.movie.entity.domain.MovieEntity;
import com.example.movie.control.exceptions.MovieException;
import com.example.movie.control.mapper.MovieMapper;
import com.example.movie.control.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;


@ApplicationScoped
@Transactional
@AllArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    public String createMovie(final Movie newMovie) {
        final Movie movie = getMovieByName(newMovie.getName());
        checkIfMovieExists(newMovie, movie);

        MovieEntity movieEntity = movieMapper.mapToEntity(newMovie);
        String movieId = movieRepository.persistMovie(movieEntity);
        log.info("Persist Movie: " + movieId + " persisted successfully");
        return movieId;
    }


    @Transactional(NOT_SUPPORTED)
    public Movie getMovieByName(String movieName) {
        MovieEntity movieByName = movieRepository.getMovieByName(movieName);

        log.info("Get Movie by name: " + movieName);
        return movieByName != null
                ? movieMapper.mapToBusiness(movieByName)
                : null;
    }

    @Transactional(NOT_SUPPORTED)
    public Movie getMovieById(String movieId) {
        MovieEntity movieById = findMovieById(movieId);

        return movieMapper.mapToBusiness(movieById);
    }

    public Movie updateMovie(Movie movie) {
        final MovieEntity movieEntity = findMovieById(movie.getId());
        final MovieEntity newMovieEntity = movieMapper.mapToEntity(movie);
        final MovieEntity updatedMovieEntity = movieRepository.updateMovie(movieEntity, newMovieEntity);

        log.info("Update Movie: " + updatedMovieEntity.id + " successfully!");
        return movieMapper.mapToBusiness(updatedMovieEntity);
    }

    public void deleteMovie(String movieId) {
        final MovieEntity movie = findMovieById(movieId);

        movieRepository.deleteMovie(movie);
        log.info("Delete Movie: " + movieId);
    }

    private MovieEntity findMovieById(String movieId) {
        final MovieEntity movieEntity = movieRepository.getMovieById(movieId);
        if (movieEntity == null) {
            log.info("Movie with id " + movieId + " not found");
            throw new MovieException("Movie with id " + movieId + " not found");
        }

        log.info("Get Movie by Id: " + movieId);
        return movieEntity;
    }

    private static void checkIfMovieExists(Movie newMovie, Movie movie) {
        if (movie != null) {
            log.info("Movie Exception: " + newMovie.getName() + " already exists!");
            throw new MovieException("Movie with designation " + newMovie.getName() + " already exists!");
        }
    }

}
