package lt.techin.media_site.validation;

import lt.techin.media_site.validation.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(),err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<ErrorResponse> handleEmailTaken(EmailTakenException ex) {
        ErrorResponse err = new ErrorResponse("Email is in use", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MediaExistsException.class)
    public ResponseEntity<ErrorResponse> handleMediaExists(MediaExistsException ex) {
        ErrorResponse err = new ErrorResponse("Show already exists", ex.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IdDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleIdDoesNotExist(IdDoesNotExistException ex) {
        ErrorResponse err = new ErrorResponse("Id does not exist", ex.getMessage());
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AccountOptionsException.class)
    public ResponseEntity<ErrorResponse> handleAccountDeletion(AccountOptionsException ex) {
        ErrorResponse err = new ErrorResponse("Cannot delete other accounts", ex.getMessage());
        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        ErrorResponse err = new ErrorResponse("Entity not found", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

}
