package net.branium.soulmusicbeservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.branium.soulmusicbeservice.mapper.PlaylistMapper;
import net.branium.soulmusicbeservice.model.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaylistRepositoryTest {

    @Autowired
    private PlaylistRepository playlistRepo;

    @Test
    public void testGetPlaylistsByUserId() throws Exception {
        var result = playlistRepo.getPlaylistsByUserId("fIKFojmlBMP5f0s8r2hYpSv32l12");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    public void testMappingPlaylist() {
        Playlist playlistFromDB = playlistRepo.findById(2).orElse(null);
        System.out.println(playlistFromDB);
    }
}