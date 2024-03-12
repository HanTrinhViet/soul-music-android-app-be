package net.branium.soulmusicbeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.branium.soulmusicbeservice.dto.UserDTO;
import net.branium.soulmusicbeservice.mapper.UserMapper;
import net.branium.soulmusicbeservice.model.User;
import net.branium.soulmusicbeservice.service.UserService;
import net.branium.soulmusicbeservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserApiController.class)
class UserApiControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private final UserMapper userMapper;

    @MockBean
    private final UserService userService;

    @Autowired
    public UserApiControllerTest(MockMvc mockMvc, ObjectMapper objectMapper,
                                 UserMapper userMapper, UserService userService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Test
    public void givenUserDetails_WhenUserCreated_ThenUserIsSaved() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username("viethantrinh")
                .email("hntrnn12@gmail.com")
                .password("MaiAnhDo06102001@")
                .build();

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username("viethantrinh")
                .email("hntrnn12@gmail.com")
                .password("MaiAnhDo06102001@")
                .build();

        when(userMapper.toUser(any(UserDTO.class))).thenReturn(user);
        when(userService.createUser(any(User.class))).thenReturn(user);
        when(userMapper.toUserDTO(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(post(Constants.FULL_PATH + "/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("viethantrinh")))
                .andExpect(jsonPath("$.email", is("hntrnn12@gmail.com")))
                .andDo(print());
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt(15, 21));
        }
    }
}