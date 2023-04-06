package com.example.room.control.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomExceptionMapper implements ExceptionMapper<RoomException> {

    @Override
    public Response toResponse(RoomException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity("Room error: " + exception.getMessage())
                .build();
    }
}

