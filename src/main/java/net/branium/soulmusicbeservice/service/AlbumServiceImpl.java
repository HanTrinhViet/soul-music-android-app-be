package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Album;
import net.branium.soulmusicbeservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepo;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepo.save(album);
    }

    @Override
    public List<Album> createAlbums(List<Album> albums) {
        return albumRepo.saveAll(albums);
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepo.findAll();
    }
}
