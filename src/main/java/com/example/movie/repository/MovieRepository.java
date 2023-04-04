package com.example.movie.repository;

import com.example.movie.repository.domain.MovieEntity;
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


    public MovieEntity getRoomByDesignation(String roomDesignation) {
        return MovieEntity.find("designation", roomDesignation).firstResult();
    }

}
