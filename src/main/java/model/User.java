package model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class User {
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String about;
    private Set<Group> groupSet;
    private Set<Follower> followers;
    private List<Message> messageList;
}
