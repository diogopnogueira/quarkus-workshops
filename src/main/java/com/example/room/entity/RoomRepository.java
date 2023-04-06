package com.example.room.entity;

import com.example.room.entity.domain.RoomEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class RoomRepository {

    @Inject
    EntityManager entityManager;

    public String persistRoom(RoomEntity roomEntity) {
        entityManager.persist(roomEntity);

        return String.valueOf(roomEntity.getId());
    }

    public RoomEntity getRoomById(String roomId) {
        return entityManager.find(RoomEntity.class, Long.parseLong(roomId));
    }

    public RoomEntity updateRoom(RoomEntity roomEntity, RoomEntity newRoomEntity) {
        roomEntity.setDesignation(newRoomEntity.getDesignation());
        roomEntity.setCurrentMovie(newRoomEntity.getCurrentMovie());
        roomEntity.setCapacity(newRoomEntity.getCapacity());
        roomEntity.setPrice(newRoomEntity.getPrice());

        return entityManager.merge(roomEntity);
    }

    public void deleteRoom(RoomEntity room) {
        entityManager.remove(room);
    }

    public RoomEntity getRoomByDesignation(String roomDesignation) {
        TypedQuery<RoomEntity> query = entityManager.createNamedQuery("RoomEntity.findByDesignation", RoomEntity.class);
        query.setParameter("designation", roomDesignation);

        return query.getSingleResult();
    }

}
