package uku.java.JavaBackend.MVC.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uku.java.JavaBackend.MVC.exceptions.ErrorMessageResponse;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageResponse> handleNotFoundException(NoSuchElementException ex) {

        ErrorMessageResponse error = new ErrorMessageResponse(
                "Data not found ",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleValidationException(MethodArgumentNotValidException ex) {

        String detailedMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorMessageResponse error = new ErrorMessageResponse(
                "Request validation error",
                detailedMessage,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                "Sever error",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
