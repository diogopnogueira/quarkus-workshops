package com.example.movie.boundary.model;

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

    @JsonProperty("movieId")
    private String id;

    @NotBlank(message = "Movie name cant be blank")
    @JsonProperty("movieName")
    private String name;

    @NotBlank(message = "Movie genre cant be blank")
    @JsonProperty("movieGenre")
    private String genre;

    @Digits(message = "Movie duration must be an integer with 3 digits", integer = 3, fraction = 0)
    @Min(60)
    @NotNull
    @JsonProperty("movieDuration")
    private int duration;


    public MovieDTO(String name, String genre, int duration) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

}
