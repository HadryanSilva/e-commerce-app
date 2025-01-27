package br.com.hadryan.ecommerce.customer.exception;

import java.util.Map;

public record MultipleErrorMessage(int code, Map<String, String> errors) {
}
