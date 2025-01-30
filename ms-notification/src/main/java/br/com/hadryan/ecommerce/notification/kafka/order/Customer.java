package br.com.hadryan.ecommerce.notification.kafka.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    @NotBlank(message = "ID is mandatory")
    private String id;
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email", regexp = "^([a-zA-Z0-9._%+-]+)(@)?([a-zA-Z0-9._%+-]+)$")
    private String email;

}
