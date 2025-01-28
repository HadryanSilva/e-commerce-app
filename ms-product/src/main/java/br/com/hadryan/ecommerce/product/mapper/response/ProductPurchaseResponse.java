package br.com.hadryan.ecommerce.product.mapper.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPurchaseResponse {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

}
