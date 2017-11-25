package com.example.archiver.library;

import com.example.archiver.models.Song;
import com.example.archiver.models.SongDao;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Audio() {}

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

    public String AddTrack(int track,
                           String title,
                           String artist,
                           String albumArtist,
                           String album,
                           int year,
                           int rawLength,
                           int bitrate,
                           int sampleRate,
                           int channels,
                           String filePath
                           )
    {

        String songId = "";
        String albumId = "";
        try
        {
            Song song = new Song(title, artist, albumArtist, album, year, rawLength, bitrate, sampleRate, channels, filePath);
            songDao.save(song);
            songId = String.valueOf(song.getId());

        } catch (Exception ex) {
            return "Error creating the user: " + ex.getMessage();
        }
        return "Song successfully created with id = " + songId;

    }

    private void readId3Tag() {

    }

    @Autowired
    private SongDao songDao;
}
