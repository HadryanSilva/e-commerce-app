package br.com.hadryan.ecommerce.order.mapper.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String id;
    private String firstname;
    private String lastname;
    private String email;

}
