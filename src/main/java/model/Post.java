package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Post {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime timeStamp;
    private Set<Long> tagIds;
}
