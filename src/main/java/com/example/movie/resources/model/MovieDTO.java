package com.example.movie.resources.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MovieDTO {

    @JsonProperty("Id")
    private String id;

    @NotBlank(message = "Name cant be blank")
    @JsonProperty("Movie_Name")
    private String name;

    @NotBlank(message = "Genre cant be blank")
    @JsonProperty("Genre_Name")
    private String genre;

    @Digits(message = "Duration must contain digits", integer = 3, fraction = 0)
    @Min(60)
    @NotNull
    @JsonProperty("Duration")
    private int duration;


    public MovieDTO(String designation, String genre, int duration) {
        this.name = designation;
        this.genre = genre;
        this.duration = duration;
    }

}
