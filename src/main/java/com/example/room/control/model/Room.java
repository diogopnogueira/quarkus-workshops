package com.example.room.control.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {

    private String id;

    private String designation;

    private String currentMovie;

    private int capacity;

    private double price;

}

