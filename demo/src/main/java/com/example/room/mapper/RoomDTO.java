package com.example.room.mapper;

public class RoomDTO {

    private String id;
    private String designation;
    private String movieName;
    private int capacity;
    private double price;

    public RoomDTO(String id, String designation, String movieName, int capacity, double price) {
        this.id = id;
        this.designation = designation;
        this.movieName = movieName;
        this.capacity = capacity;
        this.price = price;
    }

    public RoomDTO() {
    }

    public RoomDTO(String designation, String movieName, int capacity, double price) {
        this.designation = designation;
        this.movieName = movieName;
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

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
