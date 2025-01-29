package lt.techin.h2_attempt.exceptions;

public class MessageNotFoundException extends RuntimeException {
  public MessageNotFoundException(String id) {
    super(String.format("Message with id %s not found: ", id));
  }
}
