package com.example.movie.services;

import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.domain.MovieEntity;
import com.example.movie.services.exceptions.MovieException;
import com.example.movie.services.mapper.MovieMapper;
import com.example.movie.services.model.Movie;
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
        if (movie != null) {
            log.info("Movie Exception: " + newMovie.getName() + " already exists!");
            throw new MovieException("Movie with designation " + newMovie.getName() + " already exists!");
        }

        MovieEntity movieEntity = movieMapper.mapToEntity(newMovie);
        String movieId = movieRepository.persistMovie(movieEntity);
        log.info("Persist Movie: " + movieId + " persisted successfully");
        return movieId;
    }

    @Transactional(NOT_SUPPORTED)
    private Movie getMovieByName(String movieName) {
        MovieEntity movieByName = movieRepository.getRoomByDesignation(movieName);

        log.info("Get Room by Designation: " + movieByName.name);
        return movieByName != null
                ? movieMapper.mapToBusiness(movieByName)
                : null;
    }

}
