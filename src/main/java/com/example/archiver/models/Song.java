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
    private String title;

    @NotNull
    private String artist;

    // @NotNull
    //@Column(name = "album_artist")
    //private String albumArtist;

    @NotNull
    private String album;

    @NotNull
    private int year;

    @NotNull
    @Column(name = "track")
    private int trackNum;

    @NotNull
    @Column(name = "raw_length")
    private int rawLength;

    @NotNull
    private int bitrate;

    @NotNull
    @Column(name = "sample_rate")
    private int sampleRate;

    @NotNull
    private int channels;

    // @NotNull
    // @Column(name = "file_path")
    // private String filePath;

    public Song() {}

    public Song(long id) {
        this.id = id;
    }


    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public Song(String title, String artist, String album, int trackNum) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.trackNum = trackNum;
    }

    /*
    public Song(String title, String artist, String albumArtist, String album,
                int year, int trackNum, int rawLength, int bitrate, int sampleRate, int channels, String filePath)
    {
        this.title = title;
        this.artist = artist;
        this.albumArtist = albumArtist;
        this.album = album;
        this.year = year;
        this.trackNum = trackNum;
        this.rawLength = rawLength;
        this.bitrate = bitrate;
        this.sampleRate = sampleRate;
        this.channels = channels;
        this.filePath = filePath;
    }
    */

    public long getId()
    {
        return id;
    }

}
