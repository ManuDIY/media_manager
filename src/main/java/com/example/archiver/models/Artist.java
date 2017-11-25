package com.example.archiver.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "albums_artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name, bio;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }


}
