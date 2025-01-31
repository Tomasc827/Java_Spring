package lt.techin.Library.exceptions;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(long id) {
    super("Member with id " + id + " not found");
  }
}
