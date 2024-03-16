package net.branium.soulmusicbeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.branium.soulmusicbeservice.model.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @NotNull(message = "USER'S ID MUST NOT BE NULL")
    @JsonProperty(value = "id")
    private String id;

    @NotNull(message = "USERNAME MUST NOT BE NULL")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "USER'S EMAIL MUST NOT BE NULL")
    @JsonProperty("email")
    private String email;

    @JsonProperty(value = "playlists")
    private List<Playlist> playlists = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
