package net.branium.soulmusicbeservice.mapper;

import net.branium.soulmusicbeservice.dto.UserDTO;
import net.branium.soulmusicbeservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
