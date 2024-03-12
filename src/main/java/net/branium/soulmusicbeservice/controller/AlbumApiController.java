package net.branium.soulmusicbeservice.controller;

import net.branium.soulmusicbeservice.model.Album;
import net.branium.soulmusicbeservice.service.AlbumService;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PATH + "/albums")
public class AlbumApiController {

    private final AlbumService albumService;

    @Autowired
    public AlbumApiController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }
}
