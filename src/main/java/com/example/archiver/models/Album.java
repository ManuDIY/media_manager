package com.example.archiver.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private int album_artist_id, year;

    public Album() {}

    public Album(long id) {
        this.id = id;
    }

    public Album(String name, int year)
    {
        this.name = name;
        this.album_artist_id = album_artist_id;
        this.year = year;
    }

    public long getId() {
        return id;
    }

}
