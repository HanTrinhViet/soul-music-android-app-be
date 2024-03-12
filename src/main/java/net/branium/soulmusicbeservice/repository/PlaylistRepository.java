package net.branium.soulmusicbeservice.repository;

import net.branium.soulmusicbeservice.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}
