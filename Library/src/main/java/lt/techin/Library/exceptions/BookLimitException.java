package lt.techin.Library.exceptions;

public class BookLimitException extends RuntimeException {
  public BookLimitException(long id) {
    super("Member with id " + id + " has reached the rent limit of three books");
  }
}
