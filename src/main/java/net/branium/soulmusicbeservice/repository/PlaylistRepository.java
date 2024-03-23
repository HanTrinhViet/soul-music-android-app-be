package net.branium.soulmusicbeservice.repository;

import net.branium.soulmusicbeservice.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query("SELECT p FROM Playlist p WHERE p.user.id = ?1")
    List<Playlist> getPlaylistsByUserId(String uid);

}
