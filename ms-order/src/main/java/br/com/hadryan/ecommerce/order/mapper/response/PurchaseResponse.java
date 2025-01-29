package br.com.hadryan.ecommerce.order.mapper.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseResponse {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

}
