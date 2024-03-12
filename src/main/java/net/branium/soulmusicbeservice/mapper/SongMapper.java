package net.branium.soulmusicbeservice.mapper;

import net.branium.soulmusicbeservice.dto.SongDTO;
import net.branium.soulmusicbeservice.model.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toSong(SongDTO songDTO);

}
