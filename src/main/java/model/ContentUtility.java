package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentUtility {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime timeStamp;
}
