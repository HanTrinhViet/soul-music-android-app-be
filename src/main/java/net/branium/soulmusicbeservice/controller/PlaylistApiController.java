package net.branium.soulmusicbeservice.controller;

import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.service.PlaylistService;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PATH + "/playlists")
public class PlaylistApiController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistApiController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Playlist>> getPlaylistsByUserId(@PathVariable(value = "id") String uuid) {
        var playlists = playlistService.getPlaylistsByUserId(uuid);
        return ResponseEntity.ok(playlists);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist, @PathVariable("id") String uuid) {
        Playlist createdPlaylist = playlistService.createPlaylist(playlist, uuid);
        return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist) {
        Playlist updatedPlaylist = playlistService.updatePlaylist(playlist);
        return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylistById(@PathVariable(value = "id") Integer id) {
        playlistService.deletePlaylistById(id);
        return ResponseEntity.noContent().build();
    }
}
