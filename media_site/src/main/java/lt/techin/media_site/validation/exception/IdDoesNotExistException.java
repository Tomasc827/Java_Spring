package lt.techin.media_site.validation.exception;

public class IdDoesNotExistException extends RuntimeException {
    public IdDoesNotExistException(String message) {
        super(message);
    }
}
