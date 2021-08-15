package repository;

import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import model.User;

import java.util.HashSet;
import java.util.Set;

public class UserRepository {

    private Set<User> users;
    private long userId;
    private UserRepository() {
        users = new HashSet<>();
    }

    public Set<User> getUsers() {
        return users;
    }

    public int getNumberOfUsers(){
        return users.size();
    }

    public void save(User user){
        for (User existingUser : users){
            if(user.getEmail().equals(existingUser.getEmail()))
                throw new UserAlreadyExistsException("User with \"" + user.getEmail() + "\" already exists");
        }
        user.setId(++userId);
        users.add(user);
    }

    public User getUser(String email){
        for (User user : users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        throw new UserNotFoundException("No User with email \"" + email + "\" found");

    }

    public void deleteUser(String email){
        Set<User> updateUsersSet = new HashSet<>();
        for (User user : users){
            if(!user.getEmail().equals(email)){
                updateUsersSet.add(user);
            }
        }
        users = updateUsersSet;
    }

    public void clear(){
        userId = 0;
        users.clear();
    }
    private static class UserRepositoryHelper{
        private static final UserRepository instance = new UserRepository();
    }

    public static UserRepository createDatabase(){
        return UserRepositoryHelper.instance;
    }
}
