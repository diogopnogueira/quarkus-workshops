package com.example.room.boundary;

import com.example.room.boundary.assembler.RoomAssembler;
import com.example.room.boundary.model.RoomDTO;
import com.example.room.boundary.model.UpdateRoomDTO;
import com.example.room.control.RoomService;
import com.example.room.control.model.Room;
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
@Path("room")
@AllArgsConstructor
@Slf4j
public class RoomResource {

    private final RoomService roomService;

    private final RoomAssembler roomAssembler;

    @POST
    public Response createRoom(@Valid final RoomDTO newRoom) {
        final Room room = roomAssembler.assembleRoomDtoToBusiness(newRoom);
        final String roomId = roomService.createRoom(room);

        log.info("Room " + roomId + " created successfully");
        return Response.status(Status.CREATED).entity(roomId).build();
    }

    @GET
    @Path("{roomId}")
    public Response getRoomById(@PathParam("roomId") @NotBlank final String roomId) {
        final Room room = roomService.getRoomById(roomId);
        final RoomDTO roomDTO = roomAssembler.assembleToDto(room);

        log.info("Room get id " + roomDTO.getId() + " successfully");
        return Response.ok(roomDTO).build();
    }

    @PUT
    public Response updateRoom(@Valid final UpdateRoomDTO updateRoomDTO) {
        final Room room = roomAssembler.assembleUpdateRoomDtoToBusiness(updateRoomDTO);
        final Room updatedRoom = roomService.updateRoom(room);
        final RoomDTO updatedRoomDTO = roomAssembler.assembleToDto(updatedRoom);

        log.info("Room " + updatedRoomDTO.getId() + " updated successfully");
        return Response.ok(updatedRoomDTO).build();
    }

    @DELETE
    @Path("{roomId}")
    public Response deleteRoom(@PathParam("roomId") @NotBlank final String roomId) {
        roomService.deleteRoom(roomId);

        log.info("Room " + roomId + " deleted successfully");
        return Response.ok().build();
    }

}
