package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Tag {
    private Long id;
    private String name;
    Set<Long> postIds;
}
