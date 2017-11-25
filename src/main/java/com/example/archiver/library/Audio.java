package com.example.archiver.library;

import com.example.archiver.models.Song;
import com.example.archiver.models.SongDao;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

@Component
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

    private static final Logger log = LoggerFactory.getLogger(Audio.class);

    public Audio() {}

    public Audio(String title, String artist) {
        this.title = title;
        this.artist = artist;
        try {
            Song song = new Song(title.trim(), artist.trim());
            songDao.save(song);
        } catch (DataAccessResourceFailureException ex) {
            log.error("AA Error creating song (" + title + " - " + artist + "): " + ex.getMessage());
        } catch (DataAccessException ex) {
            log.error("BB Error creating song (" + title + " - " + artist + "): " + ex.getMessage());
        } catch (Exception ex) {
            log.error("CC Error creating song (" + title + " - " + artist + "): " + ex.getMessage());
            //return "Error creating the song: " + ex.getMessage();
        }
        log.error("Song successfully created with ID:");
        //return "Song successfully created with id";
    }

    public String addTrack(String title, String artist ) {
        String songId = "1";
        try
        {
            Song song = new Song(title, artist);
            songDao.save(song);
        } catch (Exception ex) {
            log.error("Error creating song (" + title + " - " + artist + "): " + ex.getMessage());
            return "Error creating the song: " + ex.getMessage();
        }
        log.error("Song successfully created with ID: " + songId);
        return "Song successfully created with id = " + songId;
    }

    /*
    public String addTrack(int track,
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

        String songId = "1";
        try
        {
            Song song = new Song(title, artist, albumArtist, album, year, track, rawLength, bitrate, sampleRate, channels, filePath);
            songDao.save(song);
            // songId = String.valueOf(song.getId());
            //log.info("Added song! " + songId);

        } catch (Exception ex) {
            log.error("Error creating song (" + title + " - " + artist + "): " + ex.getMessage());
            return "Error creating the song: " + ex.getMessage();
        }
        log.error("Song successfully created with ID: " + songId);
        return "Song successfully created with id = " + songId;

    }
    */


    @Autowired
    private SongDao songDao;
}
