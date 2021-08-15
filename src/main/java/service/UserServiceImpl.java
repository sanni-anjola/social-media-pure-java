package service;

import dto.UserDto;
import exception.UserNotFoundException;
import model.User;
import repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        if(userDto.getUsername() != null) user.setUsername(userDto.getUsername());

        repository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = user;
        return null;
    }

    @Override
    public User updateUser(String email, String firstName, String lastName, String about) {
        User user = repository.getUser(email);
        repository.getUsers().remove(user);
        if(user.getFirstName() == null && firstName != null) user.setFirstName(firstName);
        if(user.getLastName() == null && lastName != null) user.setLastName(lastName);
        if(user.getAbout() == null && about != null) user.setAbout(about);

        repository.getUsers().add(user);
        return user;
    }

    @Override
    public void deleteUser(String email) {
        repository.deleteUser(email);
    }

    public User updateUser(String email, String firstName, String lastName) {
       return updateUser(email, firstName, lastName, null);
    }

}
