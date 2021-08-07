package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Comment {
    private Long id;
    private String content;
    private Long postId;
    private Long userId;
    private LocalDateTime timeStamp;
    private Set<Like> likes;
}
