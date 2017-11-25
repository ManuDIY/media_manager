package com.example.archiver.controller;

import com.example.archiver.library.Audio;
import com.example.archiver.models.Song;
import com.example.archiver.models.SongDao;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.archiver.library.Media;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Controller
@PropertySource("classpath:global.properties")

public class MediaController {

    @Value("${library.audio.directory}")
    private String audioTarget;

    @Value("${library.video.directory}")
    private String videoTarget;

    @Value("${library.images.directory}")
    private String imagesTarget;

    private static final Logger log = LoggerFactory.getLogger(MediaController.class);

    @RequestMapping("/archive/audio")
    public String indexAudio(Map<String, Object> model)
    {

        model.put("targetDir", this.audioTarget);

        try {
            Media media = new Media();

            List mediaFound = media.getFileList("audio", this.audioTarget);

            this.saveAudio(mediaFound);

            log.info(Integer.toString(mediaFound.size()));

            model.put("media", mediaFound);
            model.put("total-files", mediaFound.size());

            return "archive-home";
        } catch (IOException e) {
            model.put("error", e);
            return "directory-error";
        }

    }

    @RequestMapping("/archive/video")
    public String indexVideo(Map<String, Object> model)
    {

        model.put("targetDir", this.videoTarget);


        try {
            Media media = new Media();

            List mediaFound = media.getFileList("video", this.videoTarget);

            log.info(Integer.toString(mediaFound.size()));

            model.put("media", mediaFound);
            model.put("total-files", mediaFound.size());
            return "archive-home";
        } catch (IOException e) {
            model.put("error", e);
            return "directory-error";
        }

    }

    @RequestMapping("/archive/images")
    public String indexImages(Map<String, Object> model)
    {
        model.put("targetDir", this.imagesTarget);

        try {
            Media media = new Media();

            List mediaFound = media.getFileList("image", this.imagesTarget);

            log.info(Integer.toString(mediaFound.size()));

            model.put("media", mediaFound);
            model.put("total-files", mediaFound.size());
            return "archive-home";

        } catch (IOException e) {
            model.put("error", e);
            return "directory-error";
        }

    }

    private void saveAudio(List<Object[]> mediaFound) {
        for (Object[] media : mediaFound) {
            File f = new File(media[3].toString());

            try {
                org.jaudiotagger.audio.AudioFile track = AudioFileIO.read(f.getAbsoluteFile());

                int trackNum        = Integer.parseInt(track.getTag().getFirst(FieldKey.TRACK));
                String title        = track.getTag().getFirst(FieldKey.TITLE);
                String artist       = track.getTag().getFirst(FieldKey.ARTIST);
                String albumArtist  = "albumArtist";
                String album        = track.getTag().getFirst(FieldKey.ALBUM);
                int year            = Integer.parseInt(track.getTag().getFirst(FieldKey.YEAR));
                int rawLength       = track.getAudioHeader().getTrackLength();
                int bitrate         = Integer.parseInt(track.getAudioHeader().getBitRate());
                int sampleRate      = Integer.parseInt(track.getAudioHeader().getSampleRate());
                int channels        = Integer.parseInt(track.getAudioHeader().getChannels());

                Song song = new Song(title, artist, album, trackNum);
                songDao.save(song);

                log.info("Added: " + title + " - " + artist + " - " + album );

            } catch (IOException e) {
                log.info(e.toString());
            } catch (Exception e) {
                log.info(e.toString());
            }
        }
    }

    @Autowired
    private SongDao songDao;

}
