package com.example.archiver.library;

public class Audio {
    private String artist;
    private String title;
    private String album;
    private int year;
    private int disc;
    private int track;
    private String comment;
    private int rawLength;
    private String length;
    private int sampleRate;
    private int bitrate;
    private int channels;
    private int filesize;

    public Audio(String artist, String title, String album, int year,
                 int disc, int track, String comment, int rawLength,
                 String length, int sampleRate, int bitrate, int channels,
                 int filesize) {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.year = year;
        this.disc = disc;
        this.track = track;
        this.comment = comment;
        this.rawLength = rawLength;
        this.length = length;
        this.sampleRate = sampleRate;
        this.bitrate = bitrate;
        this.channels = channels;
        this.filesize = filesize;
    }

    private void readId3Tag() {

    }
}
