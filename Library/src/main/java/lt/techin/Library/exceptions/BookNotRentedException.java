package lt.techin.Library.exceptions;

public class BookNotRentedException extends RuntimeException {
    public BookNotRentedException(Long id) {
        super("Book with id " + id + " is not rented");
    }
}
