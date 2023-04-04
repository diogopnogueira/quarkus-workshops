package com.example.movie.services.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MovieExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof MovieException) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Movie error: " + e.getMessage())
                    .build();
        } else {
            // return a generic error response for any other exception types
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred: " + e.getMessage())
                    .build();
        }
    }
}

