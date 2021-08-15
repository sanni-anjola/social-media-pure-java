package service;

import dto.UserDto;
import model.User;

public interface UserService {
    User createUser(UserDto userDto);

    User updateUser(User user);
    User updateUser(String email, String firstName, String lastName, String about);
    void deleteUser(String email);
    }
