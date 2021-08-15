package exception;

public class InvalidEmailException extends AppException {
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }


}
