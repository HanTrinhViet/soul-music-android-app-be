package net.branium.soulmusicbeservice.mapper;

import net.branium.soulmusicbeservice.dto.UserDTO;
import net.branium.soulmusicbeservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    @Mapping(target = "playlists", ignore = true)
    User toUpdatedUser(User userRequest);
}
