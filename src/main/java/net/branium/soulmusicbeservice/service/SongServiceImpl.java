package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Song;
import net.branium.soulmusicbeservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepo;

    @Autowired
    public SongServiceImpl(SongRepository songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public List<Song> getSongs() {
        return songRepo.findAll();
    }

    @Override
    public List<Song> createSongs(List<Song> songs) {
        return songRepo.saveAll(songs);
    }
}
