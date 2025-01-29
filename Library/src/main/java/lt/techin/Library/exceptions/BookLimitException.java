package lt.techin.Library.exceptions;

public class BookLimitException extends RuntimeException {
    public BookLimitException(String message) {
        super(message);
    }
}
