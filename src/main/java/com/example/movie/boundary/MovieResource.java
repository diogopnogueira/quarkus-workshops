package com.example.movie.boundary;

import com.example.movie.boundary.assembler.MovieAssembler;
import com.example.movie.boundary.model.MovieDTO;
import com.example.movie.boundary.model.UpdateMovieDTO;
import com.example.movie.control.MovieService;
import com.example.movie.control.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("movie")
@AllArgsConstructor
@Slf4j
public class MovieResource {

    private final MovieService movieService;

    private final MovieAssembler movieAssembler;

    @POST
    public Response createMovie(@Valid final MovieDTO movieDto) {
        final Movie movie = movieAssembler.assembleMovieDtoToBusiness(movieDto);
        final String movieId = movieService.createMovie(movie);

        log.info("Movie " + movieId + " created successfully");
        return Response.status(Status.CREATED).entity(movieId).build();
    }

    @GET
    @Path("{movieId}")
    public Response getMovieById(@PathParam("movieId") @NotBlank final String movieId) {
        final Movie movie = movieService.getMovieById(movieId);
        final MovieDTO movieDTO = movieAssembler.assembleToDto(movie);

        log.info("Movie get id " + movieDTO.getId() + " successfully");
        return Response.ok(movieDTO).build();
    }


    @PUT
    public Response updateMovie(@Valid final UpdateMovieDTO updateMovieDTO) {
        final Movie movie = movieAssembler.assembleUpdateMovieDtoToBusiness(updateMovieDTO);
        final Movie updatedMovie = movieService.updateMovie(movie);
        final MovieDTO updatedMovieDTO = movieAssembler.assembleToDto(updatedMovie);

        log.info("Movie " + updatedMovieDTO.getId() + " updated successfully");
        return Response.ok(updatedMovieDTO).build();
    }

    @DELETE
    @Path("{movieId}")
    public Response deleteMovie(@PathParam("movieId") @NotBlank final String movieId) {
        movieService.deleteMovie(movieId);

        log.info("Movie " + movieId + " deleted successfully");
        return Response.ok().build();
    }

}
