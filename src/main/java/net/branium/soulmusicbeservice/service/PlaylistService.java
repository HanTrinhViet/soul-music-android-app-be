package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist, String uuid);
    Playlist updatePlaylist(Playlist playlist);
    void deletePlaylistById(Integer id);
    List<Playlist> getPlaylistsByUserId(String uid);
}
