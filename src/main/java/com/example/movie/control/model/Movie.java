package com.example.movie.control.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

    private String id;

    private String name;

    private String genre;

    private int duration;

}

