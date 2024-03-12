package net.branium.soulmusicbeservice.repository;

import net.branium.soulmusicbeservice.bootstrap.Bootstrap;
import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.model.Song;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Test
    @Rollback(value = false)
    public void testCreateUser() {
        List<Song> songListForPlaylist1 = Bootstrap.createRandomListSong(songRepository.findAll());
        List<Song> songListForPlaylist2 = Bootstrap.createRandomListSong(songRepository.findAll());

        Playlist playlist1 = Playlist.builder()
                .image(Constants.ALBUM_URL_IMAGE_1)
                .title("thư giãn")
                .songs(songListForPlaylist1)
                .songNumber(songListForPlaylist1.size())
                .build();

        Playlist playlist2 = Playlist.builder()
                .image(Constants.ALBUM_URL_IMAGE_2)
                .title("tâm trạng")
                .songs(songListForPlaylist2)
                .songNumber(songListForPlaylist2.size())
                .build();

        User user = User.builder()
                .id("fIKFojmlBMP5f0s8r2hYpSv32l12")
                .username("viethantrinh")
                .email("hntrnn195@gmail.com")
                .password("123456")
                .playlists(Arrays.asList(playlist1, playlist2))
                .build();

        userRepository.save(user);
    }
}