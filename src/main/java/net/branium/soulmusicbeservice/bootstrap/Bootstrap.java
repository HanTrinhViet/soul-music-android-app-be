package net.branium.soulmusicbeservice.bootstrap;

import net.branium.soulmusicbeservice.dto.SongApiResponse;
import net.branium.soulmusicbeservice.dto.SongDTO;
import net.branium.soulmusicbeservice.mapper.SongMapper;
import net.branium.soulmusicbeservice.model.Album;
import net.branium.soulmusicbeservice.model.Song;
import net.branium.soulmusicbeservice.service.AlbumService;
import net.branium.soulmusicbeservice.service.SongService;
import net.branium.soulmusicbeservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class Bootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    @Bean
    public CommandLineRunner bootstrapData(RestTemplate restTemplate,
                                           SongService songService,
                                           AlbumService albumService,
                                           SongMapper songMapper) {
        return runner -> {
            List<Song> songList = new ArrayList<>();
            List<Album> albumList = new ArrayList<>();
            // handle songs
            if (songService.getSongs().isEmpty()) {
                songList = processData(restTemplate, songMapper);
                songService.createSongs(songList);
            }

            // handle albums
            songList = songService.getSongs();
            if (albumService.getAlbums().isEmpty() && !songList.isEmpty()) {
                albumList = createFakeAlbumList(songList);
                albumService.createAlbums(albumList);
            }
        };
    }

    private List<Album> createFakeAlbumList(List<Song> songList) {
        Album album1 = new Album(null, "Nhạc trẻ", Constants.ALBUM_URL_IMAGE_1, null);
        Album album2 = new Album(null, "Nhạc già", Constants.ALBUM_URL_IMAGE_2, null);
        Album album3 = new Album(null, "Nhạc rap", Constants.ALBUM_URL_IMAGE_3, null);
        Album album4 = new Album(null, "Nhạc không lời", Constants.ALBUM_URL_IMAGE_4, null);
        Album album5 = new Album(null, "Nhạc bollero", Constants.ALBUM_URL_IMAGE_5, null);
        Album album6 = new Album(null, "Nhạc lạnh", Constants.ALBUM_URL_IMAGE_6, null);
        Album album7 = new Album(null, "Nhạc nóng", Constants.ALBUM_URL_IMAGE_7, null);
        Album album8 = new Album(null, "Nhạc có màu", Constants.ALBUM_URL_IMAGE_8, null);
        Album album9 = new Album(null, "Nhạc không màu", Constants.ALBUM_URL_IMAGE_9, null);
        Album album10 = new Album(null, "Nhạc nhẽo", Constants.ALBUM_URL_IMAGE_10, null);

        List<Album> albumList = new ArrayList<>(Arrays.asList(album1, album2, album3, album4, album5,
                album6, album7, album8, album9, album10));

        albumList.forEach(album -> album.setSongs(createRandomListSong(songList)));
        return albumList;
    }

    public static List<Song> createRandomListSong(List<Song> songList) {
        Random rand = new Random();
        List<Song> randomSongList = new ArrayList<>();
        // số lượng bài trong một album sẽ dao động từ 15 -> 20 bài (tổng cộng số bài hát là 121 bài)
        int songNumber = rand.nextInt(15, 21);
        Set<Integer> indexs = new HashSet<>();
        for (int i = 1; i <= songNumber; i++) {
            int index = rand.nextInt(0, songList.size());
            while (indexs.contains(index)) {
                index = rand.nextInt(0, songList.size());
            }
            indexs.add(index);
            randomSongList.add(songList.get(index));
        }
        return randomSongList;
    }

    private List<Song> processData(RestTemplate restTemplate,
                                   SongMapper songMapper) throws Exception {
        List<Song> songList = new ArrayList<>();
        SongApiResponse songApiResponse = restTemplate.getForObject(Constants.SONG_API_PATH, SongApiResponse.class);

        if (songApiResponse != null && !songApiResponse.getSongDTOList().isEmpty()) {
            List<SongDTO> songDTOList = songApiResponse.getSongDTOList();
            songList = songDTOList.stream()
                    .map(songMapper::toSong)
                    .collect(Collectors.toList());
        } else {
            LOGGER.error("ERROR: SongApiResponse is empty!");
        }

        return songList;
    }
}
