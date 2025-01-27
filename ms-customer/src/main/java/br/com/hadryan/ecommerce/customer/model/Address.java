package br.com.hadryan.ecommerce.customer.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    private String street;
    private String number;
    private String zipCode;

}
