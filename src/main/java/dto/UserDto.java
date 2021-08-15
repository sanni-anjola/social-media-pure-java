package dto;

import exception.InvalidEmailException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class UserDto {
    private String password;
    private String email;
    private String username;

    public UserDto(String email, String username, String password){
        Pattern pattern = Pattern.compile("(\\w+)@\\w{2,5}.\\w{2,3}");
        Matcher matcher = pattern.matcher(email);
        if(matcher.find()) {
            this.email = email;
        } else {
            throw new InvalidEmailException("The email " + email + " is not a valid email");
        }
        this.username = username;
        this.password = password;
    }

    public UserDto(String email, String password){
        this(email, null, password);
    }
}
