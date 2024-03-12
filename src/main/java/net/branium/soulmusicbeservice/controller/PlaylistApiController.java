package net.branium.soulmusicbeservice.controller;

import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.service.PlaylistService;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_PATH + "/playlists")
public class PlaylistApiController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistApiController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping()
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return new ResponseEntity<>(
                playlistService.createPlaylist(playlistService.createPlaylist(playlist)),
                HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist) {
        return new ResponseEntity<>(
                playlistService.updatePlaylist(playlistService.createPlaylist(playlist)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylistById(@PathVariable(value = "id") Integer id) {
        playlistService.deletePlaylistById(id);
        return ResponseEntity.noContent().build();
    }
}
