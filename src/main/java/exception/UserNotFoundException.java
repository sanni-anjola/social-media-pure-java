package exception;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
