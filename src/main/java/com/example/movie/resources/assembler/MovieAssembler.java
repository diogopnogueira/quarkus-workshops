package com.example.movie.resources.assembler;

import com.example.movie.resources.model.MovieDTO;
import com.example.movie.services.model.Movie;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieAssembler {


    public Movie assembleMovieDtoToBusiness(MovieDTO movieDTO) {
        return new Movie(movieDTO.getId(), movieDTO.getName(), movieDTO.getGenre(), movieDTO.getDuration());
    }

//    public Movie assembleUpdateMovieDtoToBusiness(UpdateMovieDTO updateMovieDTO) {
//        return new Movie(updateMovieDTO.getId(), updateMovieDTO.getDesignation(), updateMovieDTO.getMovieName(), updateMovieDTO.getCapacity(), updateMovieDTO.getPrice());
//    }

    public MovieDTO assembleToDto(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getName(), movie.getGenre(), movie.getDuration());
    }
}
