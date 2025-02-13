package lt.techin.car_rental.validation.exception;

public class RentalLimitException extends RuntimeException {
    public RentalLimitException(String message) {
        super(message);
    }
}
