package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();
    List<Song> createSongs(List<Song> songs);

}
