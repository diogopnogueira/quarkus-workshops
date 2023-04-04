package com.example.movie.services.mapper;

import com.example.movie.repository.domain.MovieEntity;
import com.example.movie.services.model.Movie;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MovieMapper {
    
    public MovieEntity mapToEntity(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.name = movie.getName();
        movieEntity.genre = movie.getGenre();
        movieEntity.duration = movie.getDuration();

        return movieEntity;
    }

    public Movie mapToBusiness(MovieEntity movieEntity) {
        return new Movie(String.valueOf(movieEntity.id), movieEntity.name, movieEntity.genre, movieEntity.duration);
    }

}
