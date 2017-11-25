package com.example.archiver.component;

import com.example.archiver.library.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.List;


@Component
public class Watcher {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Value("${library.audio.directory}")
    private String audioTarget;

    @Value("${library.video.directory}")
    private String videoTarget;

    @Value("${library.images.directory}")
    private String imagesTarget;


    @Scheduled(cron="* * * * * *")
    public void watch(){
        // start your monitoring in here
        //define a folder root
        Path myDir = Paths.get(imagesTarget);

        log.info("=====================================================");
        log.info("Who watches the watcher....");
        log.info("=====================================================");

        try {
            WatchService watcher = myDir.getFileSystem().newWatchService();

            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watckKey = watcher.take();

            Media media = new Media();


            List<WatchEvent<?>> events = watckKey.pollEvents();
            for (WatchEvent event : events) {

                // Get full path of the file
                Path dir = (Path)watckKey.watchable();
                Path fullPath = dir.resolve((Path) event.context());

                // Add file to database
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    //log.info("Created: " + event.context().toString());
                    media.add(fullPath);
                }

                // Remove file from database
                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    log.info("Delete: " + event.context().toString());
                    log.info("Path: " + fullPath);
                }

                // Updated file record in database
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    log.info("Modify: " + event.context().toString());
                    log.info("Path: " + fullPath);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
