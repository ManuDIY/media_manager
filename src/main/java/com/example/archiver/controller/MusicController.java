package com.example.archiver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MusicController {
    @RequestMapping("/music")
    public String index() {
        return "albums-view";
    }

    @RequestMapping(value="/music/album/{id}", method=GET)
    public String getAlbum( @PathVariable("id") long id ) {
        return "album-view";
    }

    @RequestMapping(value="/music/album-artist/{id}", method=GET)
    public String getAlbumArtist(
            @PathVariable("id") long id
    ) {
        return "album-artist-view";
    }

}
