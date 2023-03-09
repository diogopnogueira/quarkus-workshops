package com.example.room.resources;

import com.example.room.resources.assembler.RoomAssembler;
import com.example.room.resources.model.RoomDTO;
import com.example.room.services.RoomService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Path("room")
public class RoomResource {

    private static final Logger log = Logger.getLogger(RoomResource.class);

    @Inject
    RoomService roomService;

    @POST
    public Response createRoom(@Valid final RoomDTO newRoom) {
        final String roomId = roomService.createRoom(RoomAssembler.assembleToBusiness(newRoom));

        log.info("Room " + roomId + " created successfully");
        return Response.status(Status.CREATED).entity(roomId).build();
    }

    @GET
    @Path("{roomId}")
    public Response getRoomById(
            @PathParam("roomId") @NotBlank final String roomId) {
        final RoomDTO room = roomService.getRoomById(roomId);

        log.info("Room get id " + room.getId() + " successfully");
        return Response.ok(room).build();
    }

    @PUT
    @Path("{roomId}")
    public Response updateRoom(
            @PathParam("roomId") @NotBlank final String roomId,
            @Valid final RoomDTO newRoom) {
        final RoomDTO updatedRoom = roomService.updateRoom(roomId, RoomAssembler.assembleToBusiness(newRoom));

        log.info("Room " + roomId + " updated successfully");
        return Response.ok(updatedRoom).build();
    }

    @DELETE
    @Path("{roomId}")
    public Response deleteRoom(
            @PathParam("roomId") @NotBlank final String roomId) {
        roomService.deleteRoom(roomId);

        log.info("Room " + roomId + " deleted successfully");
        return Response.ok().build();
    }

}
