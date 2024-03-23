package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.exception.PlaylistNotFoundException;
import net.branium.soulmusicbeservice.exception.UserNotFoundException;
import net.branium.soulmusicbeservice.mapper.PlaylistMapper;
import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.repository.PlaylistRepository;
import net.branium.soulmusicbeservice.repository.UserRepository;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private PlaylistRepository playlistRepo;
    private UserRepository userRepo;
    private PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepo, UserRepository userRepo, PlaylistMapper playlistMapper) {
        this.playlistRepo = playlistRepo;
        this.userRepo = userRepo;
        this.playlistMapper = playlistMapper;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, String uuid) {
        User userFromDB = userRepo.findById(uuid).orElse(null);
        if (userFromDB == null) {
            throw new UserNotFoundException("Can not found with id = " + uuid);
        }

        playlist.setImage(Constants.ALBUM_URL_IMAGE_5);
        playlist.setUser(userFromDB);
        return playlistRepo.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist) {
        Playlist updatedPlaylist = playlistRepo.findById(playlist.getId()).orElse(null);
        if (updatedPlaylist == null) {
            throw new PlaylistNotFoundException("Can not found playlist with id = " + playlist.getId());
        }
        updatedPlaylist.setSongNumber(playlist.getSongNumber());
        updatedPlaylist.setSongs(playlist.getSongs());
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

    @Override
    public List<Playlist> getPlaylistsByUserId(String uid) {
        var result = playlistRepo.getPlaylistsByUserId(uid);
        if (result == null) {
            throw new UserNotFoundException("Can not found with id = " + uid);
        }
        return result;
    }
}
