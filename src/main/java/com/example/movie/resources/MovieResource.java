package com.example.movie.resources;

import com.example.movie.resources.assembler.MovieAssembler;
import com.example.movie.resources.model.MovieDTO;
import com.example.movie.services.MovieService;
import com.example.movie.services.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

//    @GET
//    @Path("{movieId}")
//    public Response getMovieById(@PathParam("movieId") @NotBlank final String movieId) {
//    }
//
//    @PUT
//    public Response updateMovie(@Valid final UpdateMovieDTO updateMovieDTO) {
//    }
//
//    @DELETE
//    @Path("{movieId}")
//    public Response deleteMovie(@PathParam("movieId") @NotBlank final String movieId) {
//    }

}
