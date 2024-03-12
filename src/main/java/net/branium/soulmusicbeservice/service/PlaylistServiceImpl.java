package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.exception.PlaylistNotFoundException;
import net.branium.soulmusicbeservice.exception.UserNotFoundException;
import net.branium.soulmusicbeservice.mapper.PlaylistMapper;
import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private PlaylistRepository playlistRepo;
    private PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepo, PlaylistMapper playlistMapper) {
        this.playlistRepo = playlistRepo;
        this.playlistMapper = playlistMapper;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepo.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist) {
        Playlist updatedPlaylist = playlistRepo.findById(playlist.getId()).orElse(null);
        if (updatedPlaylist == null) {
            throw new PlaylistNotFoundException("Can not found playlist with id = " + playlist.getId());
        }
        updatedPlaylist = playlistMapper.toUpdatedPlaylist(playlist);
        return playlistRepo.save(updatedPlaylist);
    }

    @Override
    public void deletePlaylistById(Integer id) {
        Playlist playlist = playlistRepo.findById(id).orElse(null);
        if (playlist == null) {
            throw new PlaylistNotFoundException("Can not found playlist with id = " + id);
        }
        playlistRepo.deleteById(id);
    }
}
