package com.example.room.resources.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RoomDTO {

    private String id;

    @NotBlank(message = "Designation cant be blank")
//    @JsonProperty("description")
    private String designation;

    @NotBlank(message = "Movie Name cant be blank")
    private String movieName;

    @Digits(message = "Capacity must contain digits", integer = 4, fraction = 0)
    @Min(20)
    @NotNull
    private int capacity;

    @Digits(message = "Price must be a decimal number with 2 fractional digits ", integer = 2, fraction = 2)
    @Min(5)
    @NotNull
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

    public String getDesignation() {
        return designation;
    }

    public String getMovieName() {
        return movieName;
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

        RoomDTO roomDTO = (RoomDTO) o;

        if (capacity != roomDTO.capacity) return false;
        if (Double.compare(roomDTO.price, price) != 0) return false;
        if (!Objects.equals(id, roomDTO.id)) return false;
        if (!Objects.equals(designation, roomDTO.designation)) return false;
        return Objects.equals(movieName, roomDTO.movieName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result + capacity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
