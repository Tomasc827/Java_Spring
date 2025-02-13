package lt.techin.car_rental.validation;

import lt.techin.car_rental.validation.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(),err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists( AlreadyExistsException exception) {
        ErrorResponse err = new ErrorResponse("Entity Already exists", exception.getMessage());
        return new  ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoLicenseException.class)
    public ResponseEntity<ErrorResponse> handleNoLicense(NoLicenseException exception) {
        ErrorResponse err = new ErrorResponse("No license", exception.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    // rest of stuff TODO

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleRest(Exception e) {
        ErrorResponse err = new ErrorResponse("Internal Server Error", e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CarRentedException.class)
    public ResponseEntity<ErrorResponse> handleCarRented(CarRentedException e) {
        ErrorResponse err = new ErrorResponse("Car is rented", e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarRented(NotFoundException e) {
        ErrorResponse err = new ErrorResponse("Entity does not exist", e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalExistsException.class)
    public ResponseEntity<ErrorResponse> handleRentalExists(RentalExistsException e) {
        ErrorResponse err = new ErrorResponse("Error validating rental request",e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RentalLimitException.class)
    public ResponseEntity<ErrorResponse> handleRentalLimit(RentalLimitException e) {
        ErrorResponse err = new ErrorResponse("Error validating rental request",e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RentalPeriodException.class)
    public ResponseEntity<ErrorResponse> handleRentalPeriod(RentalPeriodException e) {
        ErrorResponse err = new ErrorResponse("Error validating rental request",e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OwnershipException.class)
    public ResponseEntity<ErrorResponse> handleOwnership(OwnershipException e) {
        ErrorResponse err =new ErrorResponse("Error validating user ownership",e.getMessage()) ;
            return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(CarNeverRentedException.class)
    public ResponseEntity<ErrorResponse> handleCarNeverRented(CarNeverRentedException e) {
        ErrorResponse err =new ErrorResponse("Error validating car status",e.getMessage()) ;
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

}
