package com.example.movie.control.mapper;

import com.example.movie.entity.domain.MovieEntity;
import com.example.movie.control.model.Movie;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MovieMapper {

    public MovieEntity mapToEntity(Movie movie) {
        return new MovieEntity(movie.getName(),
                movie.getGenre(),
                movie.getDuration());
    }

    public Movie mapToBusiness(MovieEntity movieEntity) {
        return new Movie(String.valueOf(movieEntity.getId()),
                movieEntity.getName(),
                movieEntity.getGenre(),
                movieEntity.getDuration());
    }

}
