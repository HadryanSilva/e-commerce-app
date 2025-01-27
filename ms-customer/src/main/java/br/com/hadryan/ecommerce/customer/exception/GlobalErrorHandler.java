package br.com.hadryan.ecommerce.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<DefaultErrorMessage> handleCustomerNotFoundException(CustomerNotFoundException e) {
        var response = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var response = new MultipleErrorMessage(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
