package br.com.hadryan.ecommerce.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotFoundException extends ResponseStatusException {

    public CustomerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
