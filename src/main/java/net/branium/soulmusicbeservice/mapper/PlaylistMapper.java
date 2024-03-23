package net.branium.soulmusicbeservice.mapper;

import net.branium.soulmusicbeservice.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist toUpdatedPlaylist(Playlist playlistFromRequest);
}
