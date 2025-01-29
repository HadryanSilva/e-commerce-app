package br.com.hadryan.ecommerce.order.mapper.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineResponse {

    private Long id;
    private Integer quantity;

}
