package lt.techin.media_site.validation.exception;

public class MediaExistsException extends RuntimeException {
    public MediaExistsException(String message) {
        super(message);
    }
}
