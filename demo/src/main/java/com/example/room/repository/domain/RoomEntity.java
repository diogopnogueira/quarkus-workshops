package com.example.room.repository.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 * <p>
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 * <p>
 * Usage (more example on the documentation)
 * <p>
 * {@code
 * public void doSomething() {
 * MyEntity entity1 = new MyEntity();
 * entity1.field = "field-1";
 * entity1.persist();
 * <p>
 * List<MyEntity> entities = MyEntity.listAll();
 * }
 * }
 */
@Entity(name = "room")
public class RoomEntity extends PanacheEntity{

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

    public RoomEntity() {
    }

}
