package com.example.movie.boundary.assembler;

import com.example.movie.boundary.model.MovieDTO;
import com.example.movie.boundary.model.UpdateMovieDTO;
import com.example.movie.control.model.Movie;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieAssembler {


    public Movie assembleMovieDtoToBusiness(MovieDTO movieDTO) {
        return new Movie(movieDTO.getId(), movieDTO.getName(), movieDTO.getGenre(), movieDTO.getDuration());
    }

    public Movie assembleUpdateMovieDtoToBusiness(UpdateMovieDTO updateMovieDTO) {
        return new Movie(updateMovieDTO.getId(), updateMovieDTO.getName(), updateMovieDTO.getGenre(), updateMovieDTO.getDuration());
    }

    public MovieDTO assembleToDto(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getName(), movie.getGenre(), movie.getDuration());
    }
}
