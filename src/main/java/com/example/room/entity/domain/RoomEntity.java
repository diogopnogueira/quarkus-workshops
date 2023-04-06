package com.example.room.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Objects;


@Entity(name = "room")
@NamedQuery(name = "RoomEntity.findByDesignation", query = "SELECT r FROM room r WHERE r.designation = :designation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "currentMovie")
    private String currentMovie;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "price")
    private double price;

    public RoomEntity(String designation, String currentMovie, int capacity, double price) {
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
