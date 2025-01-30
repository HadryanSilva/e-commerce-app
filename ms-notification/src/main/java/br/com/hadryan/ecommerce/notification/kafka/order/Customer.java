package br.com.hadryan.ecommerce.notification.kafka.order;

import jakarta.validation.constraints.Email;

public record Customer(
        String id,
        String firstname,
        String lastname,
        @Email(message = "Invalid email", regexp = "^([a-zA-Z0-9._%+-]+)(@)?([a-zA-Z0-9._%+-]+)$")
        String email
) {

}
