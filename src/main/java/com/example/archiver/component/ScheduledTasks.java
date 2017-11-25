package com.example.archiver.component;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.example.archiver.controller.MediaController;
import com.example.archiver.library.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value("${library.audio.directory}")
    private String audioTarget;

    @Value("${library.video.directory}")
    private String videoTarget;

    @Value("${library.images.directory}")
    private String imagesTarget;

    @Scheduled(cron="* 12 * * * *")
    public void reportCurrentTime() {

        Media media = new Media();

        //log.info("Scanning files - start: {}", dateFormat.format(new Date()));
        log.info("Scanning files...");

        try {
            media.getFileList("audio", audioTarget);
            media.getFileList("video", videoTarget);
            media.getFileList("image", imagesTarget);
        } catch (IOException e) {
            log.info(e.toString());
        }
    }

}
