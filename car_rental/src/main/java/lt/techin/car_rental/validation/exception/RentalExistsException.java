package lt.techin.car_rental.validation.exception;

public class RentalExistsException extends RuntimeException {
    public RentalExistsException(String message) {
        super(message);
    }
}
