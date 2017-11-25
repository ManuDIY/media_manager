package com.example.archiver.library;

import com.example.archiver.component.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Media {

    private List<String[]> results = new ArrayList<>();
    private int totalFiles = 0;
    private static final Logger log = LoggerFactory.getLogger(Media.class);

    private final String[] supportedAudio = {"mp3","flac","wav","ogg"};
    private final String[] supportedVideo = {"mkv","mp4","wbem"};
    private final String[] supportedImages = {"jpg","jpeg","gif","png","tif"};
    private String contentType = "";

    public List<String[]> getFileList(String contentType, String targetDir) throws IOException {

        this.contentType = contentType;

        if(new File(targetDir).exists()) {
            log.info("Failed to find: " + targetDir);
        }

        Files.walk(Paths.get(targetDir), 4)
                .filter(Files::isRegularFile)
                .forEach(this::indexMedia);

        log.info("Found {} {} files", this.results.size(), contentType);

        log.info("------" + Integer.toString(this.results.size()));

        return this.results;
    }

    public int getTotalFiles()
    {
        return this.totalFiles;
    }


    private boolean isAudio(String filename) {
        for(String ctype : this.supportedAudio) {
            if (filename.endsWith(ctype)) {
                return true;
            }
        }
        return false;
    }

    private boolean isImage(String filename) {
        for(String ctype : this.supportedImages) {
            if (filename.endsWith(ctype)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVideo(String filename) {
        for(String ctype : this.supportedVideo) {
            if (filename.endsWith(ctype)){
                return true;
            }
        }
        return false;
    }

    public void add(Path path) {
        boolean supportedFileType = false;
        if (isAudio(path.toString())) { this.contentType = "audio"; supportedFileType = true; }
        if (isImage(path.toString())) { this.contentType = "image"; supportedFileType = true; }
        if (isVideo(path.toString())) { this.contentType = "video"; supportedFileType = true; }

        if (supportedFileType) { this.indexMedia(path); }
    }

    private void indexMedia(Path path) {

        File f = path.toAbsolutePath().toFile();
        Date time=new java.util.Date((long)f.lastModified()*1000);

        String[] supportedFormats = {};

        switch(this.contentType){
            case "audio":
                supportedFormats = supportedAudio;
                break;
            case "video":
                supportedFormats = supportedVideo;
                break;
            case "image":
                supportedFormats = supportedImages;
        }

        boolean isSupported = false;


        for (String format : supportedFormats ) {
            if (f.getName().endsWith(format)) {
                isSupported = true;
                break;
            }
        }

        if (isSupported) {
            String[] meta = new String[5];
            meta[0] = f.getName();
            meta[1] = time.toString();
            meta[2] = Long.toString(f.length());
            meta[3] = f.getAbsolutePath();

            int track = 0;
            String title = "title";
            String artist = "artist";
            String albumArtist = "albumArtist";
            String album = "album";
            int year = 1900;
            int rawLength = 0;
            int bitrate = 0;
            int sampleRate = 0;
            int channels = 0;
            String filePath = f.getAbsolutePath();

            log.info("Adding " + this.contentType + " file: " + f.getName());

            this.results.add(meta);

            Audio audio = new Audio();

            audio.AddTrack(track,title,artist,albumArtist,album,year,rawLength,bitrate,sampleRate,channels,filePath);
        }
    }
}
