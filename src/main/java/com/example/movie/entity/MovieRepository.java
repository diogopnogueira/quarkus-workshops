package com.example.movie.entity;

import com.example.movie.entity.domain.MovieEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class MovieRepository implements PanacheRepository<MovieEntity> {

    public String persistMovie(MovieEntity movieEntity) {
        movieEntity.persistAndFlush();

        log.info("Persist: " + movieEntity.id + " successful");
        return String.valueOf(movieEntity.id);
    }


    public MovieEntity getMovieByName(String movieName) {
        return MovieEntity.find("name", movieName).firstResult();
    }

    public MovieEntity getMovieById(String movieId) {
        return findById(Long.valueOf(movieId));
    }

    public MovieEntity updateMovie(MovieEntity movieEntity, MovieEntity newMovieEntity) {
        movieEntity.name = newMovieEntity.name;
        movieEntity.genre = newMovieEntity.genre;
        movieEntity.duration = newMovieEntity.duration;
        movieEntity.persistAndFlush();

        return movieEntity;
    }

    public void deleteMovie(MovieEntity movie) {
        deleteById(movie.id);
    }
}
