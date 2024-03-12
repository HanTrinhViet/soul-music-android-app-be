package net.branium.soulmusicbeservice.mapper;

import net.branium.soulmusicbeservice.model.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist toUpdatedPlaylist(Playlist playlistFromRequest);
}
