package br.com.hadryan.ecommerce.product.mapper.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Integer availableQuantity;
    private BigDecimal price;
    private String category;

}
