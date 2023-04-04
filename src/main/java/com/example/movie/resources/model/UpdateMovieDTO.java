package com.example.movie.resources.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieDTO {

    @NotBlank(message = "Id cant be blank")
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

}