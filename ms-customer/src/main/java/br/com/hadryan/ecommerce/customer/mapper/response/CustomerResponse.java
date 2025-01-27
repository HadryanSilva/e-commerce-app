package br.com.hadryan.ecommerce.customer.mapper.response;

import br.com.hadryan.ecommerce.customer.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

}
