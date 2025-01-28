package br.com.hadryan.ecommerce.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {

    public ProductNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
