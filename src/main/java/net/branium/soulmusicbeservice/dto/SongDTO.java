package net.branium.soulmusicbeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("album")
    private String album;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("source")
    private String source;

    @JsonProperty("image")
    private String image;

    @JsonProperty("duration")
    private Integer duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongDTO that = (SongDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SongResponseDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", source='" + source + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                '}';
    }
}
