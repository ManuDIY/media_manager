package com.example.archiver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.archiver.library.Media;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
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

}
