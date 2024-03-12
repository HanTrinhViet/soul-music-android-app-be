package net.branium.soulmusicbeservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private List<String> errors = new ArrayList<>();
    private String path;
}
