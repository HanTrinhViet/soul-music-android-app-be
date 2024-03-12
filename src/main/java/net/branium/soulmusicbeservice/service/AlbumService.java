package net.branium.soulmusicbeservice.service;

import net.branium.soulmusicbeservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumService {
    Album createAlbum(Album album);
    List<Album> createAlbums(List<Album> albums);
    List<Album> getAlbums();
}
