package net.branium.soulmusicbeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongApiResponse {

    @JsonProperty("song")
    private List<SongDTO> songDTOList = new ArrayList<>();

    @Override
    public String toString() {
        return "SongResponse{" +
                "songs=" + songDTOList +
                '}';
    }
}
