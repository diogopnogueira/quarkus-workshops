package com.example.room.services.model;

import java.util.Objects;

public class Room {

    private String id;

    private String designation;

    private String currentMovie;

    private int capacity;

    private double price;

    public Room(String id, String designation, String currentMovie, int capacity, double price) {
        this.id = id;
        this.designation = designation;
        this.currentMovie = currentMovie;
        this.capacity = capacity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCurrentMovie() {
        return currentMovie;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (capacity != room.capacity) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (!Objects.equals(id, room.id)) return false;
        if (!Objects.equals(designation, room.designation)) return false;
        return Objects.equals(currentMovie, room.currentMovie);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (currentMovie != null ? currentMovie.hashCode() : 0);
        result = 31 * result + capacity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
