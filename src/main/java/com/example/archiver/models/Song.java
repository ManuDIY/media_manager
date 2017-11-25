package com.example.archiver.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String title, filePath, genre;

    @NotNull
    private int track;

    @NotNull
    @Column(name = "artist")
    private String artist;

    @NotNull
    @Column(name = "album")
    private String album;

    @NotNull
    @Column(name = "album_artist")
    private String albumArtist;

    @NotNull
    @Column(name = "sample_rate")
    private int sampleRate;

    @NotNull
    @Column(name = "raw_length")
    private int rawLength;

    @NotNull
    private int year, channels, bitrate, filesize;

    public Song() {
    }

    public Song(String title, String artist, String albumArtist, String album,
                int year, int rawLength, int bitrate, int sampleRate, int channels, String filePath)
    {
        this.title = title;
        this.artist = artist;
        this.albumArtist = albumArtist;
        this.album = album;
        this.year = year;
        this.rawLength = rawLength;
        this.bitrate = bitrate;
        this.sampleRate = sampleRate;
        this.channels = channels;
        this.filePath = filePath;
    }

    public long getId()
    {
        return id;
    }

}
