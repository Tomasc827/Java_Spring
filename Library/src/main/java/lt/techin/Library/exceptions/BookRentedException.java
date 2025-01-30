package lt.techin.Library.exceptions;

public class BookRentedException extends RuntimeException {
    public BookRentedException(Long id) {
        super("Book with the id " + id + " is already rented");
    }
}
