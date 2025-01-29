package lt.techin.h2_attempt.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MessageNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> messageNotFound(MessageNotFoundException messageNotFoundException) {
    return new ResponseEntity<>(messageNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

}
