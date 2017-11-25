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

    public Album(String name, int album_artist_id, int year)
    {
        this.name = name;
        this.album_artist_id = album_artist_id;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbum_artist_id() {
        return album_artist_id;
    }

    public void setAlbum_artist_id(int album_artist_id) {
        this.album_artist_id = album_artist_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
