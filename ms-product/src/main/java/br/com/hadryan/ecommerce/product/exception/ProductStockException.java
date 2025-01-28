package br.com.hadryan.ecommerce.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductStockException extends ResponseStatusException {

    public ProductStockException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
