package net.branium.soulmusicbeservice.repository;

import net.branium.soulmusicbeservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
