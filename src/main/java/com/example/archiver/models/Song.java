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
    private String title, composer, producer, comment, filepath, genre;

    @NotNull
    private int track;

    @NotNull
    @Column(name = "artist_id")
    private int artistId;

    @NotNull
    @Column(name = "album_id")
    private int albumId;

    @NotNull
    @Column(name = "album_artist_id")
    private int albumArtistId;

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

    public Song(long id) {
        this.id = id;
    }

    public Song(String title, int track, String composer, String producer, String comment, String filepath, String genre, int artistId, int albumId, int albumArtistId, int sampleRate, int rawLength, int year, int channels, int bitrate, int filesize) {
        this.title = title;
        this.track = track;
        this.composer = composer;
        this.producer = producer;
        this.comment = comment;
        this.filepath = filepath;
        this.genre = genre;
        this.artistId = artistId;
        this.albumId = albumId;
        this.albumArtistId = albumArtistId;
        this.sampleRate = sampleRate;
        this.rawLength = rawLength;
        this.year = year;
        this.channels = channels;
        this.bitrate = bitrate;
        this.filesize = filesize;
    }

}
