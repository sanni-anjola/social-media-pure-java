package model;

import dto.UserDto;
import exception.AppException;
import exception.UserAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    private Platform platform;
    @BeforeEach
    void setUp() {
        platform = Platform.createPlatform();

    }

    @AfterEach
    void tearDown() {

        platform.refreshPlatform();
        platform = null;
    }

    @Test
    void testThatOnlyOnePlatformIsCreated(){
        Platform platform1 = Platform.createPlatform();

        assertThat(platform, is(sameInstance(platform1)));
    }

    @Test
    void testThatPlatformHasADatabase(){
        assertThat(platform.getDatabase(), sameInstance(UserRepository.createDatabase()));
        assertThat(platform.getDatabase(), is(notNullValue()));
    }

    @Test
    void testThatUsersCanRegisterOnThePlatform(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);

        assertThat(1, is(platform.getDatabase().getNumberOfUsers()));
        assertThat(platform.getDatabase().getUsers(), hasSize(1));
        assertThat(1L, is(platform.getDatabase().getUser("sanni.anjola@gmail.com").getId()));

        UserDto ajideDto = new UserDto("ajide.dev@gmail.com", "anjola");

        platform.register(ajideDto);
        assertThat(2, equalTo(platform.getDatabase().getNumberOfUsers()));
        assertThat(2L, is(platform.getDatabase().getUser("ajide.dev@gmail.com").getId()));

    }

    @Test
    void testThatUsersCannotRegisterWith_ifEmailAlreadyExist(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);

        assertThat(1, is(platform.getDatabase().getNumberOfUsers()));
        assertThat(1L, is(platform.getDatabase().getUser("sanni.anjola@gmail.com").getId()));

        UserDto ajideDto = new UserDto("sanni.anjola@gmail.com", "anjola");

        Throwable thrown = assertThrows(UserAlreadyExistsException.class, () -> platform.register(ajideDto));
        assertThat(thrown.getMessage(), is("User with \"" + ajideDto.getEmail() + "\" already exists"));
//        assertThat(thrown.getCause(), instanceOf(UserAlreadyExistsException.class));
        assertThat(1, is(platform.getDatabase().getNumberOfUsers()));

        UserDto ajide2Dto = new UserDto("ajide.dev@gmail.com", "anjola");

        platform.register(ajide2Dto);
        assertThat(2, equalTo(platform.getDatabase().getNumberOfUsers()));
        assertThat(2L, is(platform.getDatabase().getUser("ajide.dev@gmail.com").getId()));

    }

    @Test
    void testThatUserIsLoggedInOnRegister(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);
        User user = platform.getDatabase().getUser(anjolaDto.getEmail());
        assertThat(true, is(user.isLoggedIn()));
    }

    @Test
    void testThatUserCanLogInAndLogOut(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);
        User user = platform.getDatabase().getUser(anjolaDto.getEmail());
        assertThat(true, is(user.isLoggedIn()));

        platform.logOut(user);
        assertThat(false, is(user.isLoggedIn()));

        platform.logIn(user.getEmail(), user.getPassword());
        assertThat(true, is(user.isLoggedIn()));

        platform.logOut(user);
        assertThat(false, is(user.isLoggedIn()));
    }

    @Test
    void testThatUserCanUpdateProfile(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);
        User user = platform.getDatabase().getUser(anjolaDto.getEmail());

        User updatedUser = platform.updateUserProfile(user.getEmail(), "Lateef", "Sanni", "A seasoned software engineer");

        assertThat(updatedUser.getAbout(), equalTo("A seasoned software engineer"));
        assertThat(updatedUser.getFirstName(), equalTo("Lateef"));
        assertThat(updatedUser.getLastName(), equalTo("Sanni"));
    }

    @Test
    void testThatUsersCanBeDeletedFromThePlatform(){
        UserDto anjolaDto = new UserDto("sanni.anjola@gmail.com", "anjola");
        platform.register(anjolaDto);

        assertThat(platform.getDatabase().getUsers(), hasSize(1));
        assertThat(1L, is(platform.getDatabase().getUser("sanni.anjola@gmail.com").getId()));

        UserDto ajideDto = new UserDto("ajide.dev@gmail.com", "anjola");

        platform.register(ajideDto);
        assertThat(platform.getDatabase().getUsers(), hasSize(2));
        assertThat(2L, is(platform.getDatabase().getUser("ajide.dev@gmail.com").getId()));
        System.out.println(platform.getDatabase().getUsers());

        platform.deleteUserWith(ajideDto.getEmail());
        System.out.println(platform.getDatabase().getUsers());
        assertThat(platform.getDatabase().getUsers(), hasSize(1));

    }
}