package com.example.room.repository.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "room")
@NoArgsConstructor
public class RoomEntity extends PanacheEntity {

    @Column(name = "designation")
    public String designation;

    @Column(name = "currentMovie")
    public String currentMovie;

    @Column(name = "capacity")
    public int capacity;

    @Column(name = "price")
    public double price;

    public RoomEntity(Long id, String designation, String currentMovie, int capacity, double price) {
        this.id = id;
        this.designation = designation;
        this.currentMovie = currentMovie;
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
