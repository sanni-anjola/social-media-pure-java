package exception;

public class UserAlreadyExistsException extends AppException {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
