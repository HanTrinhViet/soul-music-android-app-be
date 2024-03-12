package net.branium.soulmusicbeservice.controller;

import net.branium.soulmusicbeservice.dto.UserDTO;
import net.branium.soulmusicbeservice.mapper.UserMapper;
import net.branium.soulmusicbeservice.model.Playlist;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.service.UserService;
import net.branium.soulmusicbeservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PATH + "/users")
public class UserApiController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserApiController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User createdUser = userService.createUser(user);
        UserDTO userResponse = userMapper.toUserDTO(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/playlists")
    public ResponseEntity<List<Playlist>> getUserPlaylistById(@PathVariable(value = "id") String uuid) {
        User user = userService.getUserById(uuid);
        return ResponseEntity.ok(userMapper.toUserDTO(user).getPlaylists());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") String uuid) {
        userService.deleteUserById(uuid);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserDetailById(@PathVariable(value = "id") String uuid) {
//
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<User>> getAllUsers() {
//
//    }

}
