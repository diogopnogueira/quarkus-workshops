package com.example.room.resources.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UpdateRoomDTO {

    @NotBlank
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

    public UpdateRoomDTO(String id, String designation, String movieName, int capacity, double price) {
        this.id = id;
        this.designation = designation;
        this.movieName = movieName;
        this.capacity = capacity;
        this.price = price;
    }

    public UpdateRoomDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateRoomDTO that = (UpdateRoomDTO) o;

        if (capacity != that.capacity) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(designation, that.designation)) return false;
        return Objects.equals(movieName, that.movieName);
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