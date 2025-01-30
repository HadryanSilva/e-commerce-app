package br.com.hadryan.ecommerce.customer.mapper.request;

import br.com.hadryan.ecommerce.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email", regexp = "^([a-zA-Z0-9._%+-]+)(@)?([a-zA-Z0-9._%+-]+)$")
    private String email;
    @NotNull(message = "Address is required")
    private Address address;

}
