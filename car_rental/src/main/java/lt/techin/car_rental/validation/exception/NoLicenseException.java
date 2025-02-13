package lt.techin.car_rental.validation.exception;

public class NoLicenseException extends RuntimeException {
    public NoLicenseException(String message) {
        super(message);
    }
}
