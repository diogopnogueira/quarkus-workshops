package com.example.room.resources;

import com.example.room.mapper.RoomDTO;
import com.example.room.services.RoomService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("room")
public class RoomResource {

    @Inject
    private RoomService roomService;

    @POST
    public Response createRoom(RoomDTO roomDTO) {
        String id = roomService.createRoom(roomDTO);
        return Response.status(201).entity(id).build();
    }

    @GET
    @Path("{id}")
    public Response getRoomById(@PathParam("id") String roomId) {
        RoomDTO room = roomService.getRoomById(roomId);
        return Response.ok(room).build();
    }

    @PUT
    @Path("{id}")
    public Response updateRoom(@PathParam("id") String id, RoomDTO roomDTO) {
        if (roomService.getRoomById(id) == null)
            return Response.status(Status.NOT_FOUND).build();
        roomDTO.setId(id);
        RoomDTO updatedRoom = roomService.updateRoom(roomDTO);
        return Response.ok(updatedRoom).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteRoom(@PathParam("id") String roomId) {
        roomService.deleteRoom(roomId);
        return Response.ok().build();
    }


}
