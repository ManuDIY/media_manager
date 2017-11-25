package com.example.archiver.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "albums_artists")
public class AlbumArtist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name, bio;

    public AlbumArtist() {}

    public AlbumArtist(String name) {
        this.name = name;
    };

    public AlbumArtist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }


}
