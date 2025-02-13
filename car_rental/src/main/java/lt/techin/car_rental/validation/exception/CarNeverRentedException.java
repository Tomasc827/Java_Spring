package lt.techin.car_rental.validation.exception;

public class CarNeverRentedException extends RuntimeException {
    public CarNeverRentedException(String message) {
        super(message);
    }
}
