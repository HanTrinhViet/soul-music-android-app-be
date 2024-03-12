package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Playlist;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Playlist updatePlaylist(Playlist playlist);
    void deletePlaylistById(Integer id);
}
