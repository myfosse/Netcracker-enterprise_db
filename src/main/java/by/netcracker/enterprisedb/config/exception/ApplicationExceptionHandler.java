package by.netcracker.enterprisedb.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/** @author Andrey Egorov */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<?> handleThereIsNoSuchUserException() {
    return new ResponseEntity<>(
        new EntityNotFoundException("There is no such entity"), HttpStatus.NOT_FOUND);
  }
}
