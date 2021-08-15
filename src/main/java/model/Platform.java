package model;

import dto.UserDto;
import repository.UserRepository;
import service.UserService;
import service.UserServiceImpl;


public class Platform {

    private final UserRepository database;
    private UserService userService;

//    private static Platform uniqueInstance;
    private Platform() {
        database = UserRepository.createDatabase();
        userService = new UserServiceImpl(database);
    }

    public void register(UserDto userDto) {
        userService.createUser(userDto);
        logIn(userDto.getEmail(), userDto.getPassword());
    }


    public void logIn(String email, String password){
        User user = database.getUser(email);
        if(!user.isLoggedIn())
            user.setLoggedIn(true);
    }

    public void logOut(User user){
        if(user.isLoggedIn())
            user.setLoggedIn(false);
    }

    public User updateUserProfile(String email, String firstName, String lastName, String about){
        if(database.getUser(email).isLoggedIn())
            return userService.updateUser(email, firstName, lastName, about);
        return null;
    }
    public UserRepository getDatabase() {
        return database;
    }

    public void refreshPlatform(){
        database.clear();
    }

    public void deleteUserWith(String email) {
        userService.deleteUser(email);
    }


    /*    public static Platform createPlatform(){
                    if(uniqueInstance == null) {
                        uniqueInstance = new Platform();
                    }
                    return uniqueInstance;
                }*/
    private static class PlatformSingletonHelper{
        private static final Platform instance = new Platform();
    }

    public static Platform createPlatform(){
        return PlatformSingletonHelper.instance;
    }


}
