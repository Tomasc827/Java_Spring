package lt.techin.Online_Course_Platform.validation;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
