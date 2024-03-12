package net.branium.soulmusicbeservice.controller;

import net.branium.soulmusicbeservice.model.Song;
import net.branium.soulmusicbeservice.service.SongService;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PATH + "/songs")
public class SongApiController {
    private final SongService songService;

    @Autowired
    public SongApiController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songList = songService.getSongs();
        return ResponseEntity.ok(songList);
    }


}
