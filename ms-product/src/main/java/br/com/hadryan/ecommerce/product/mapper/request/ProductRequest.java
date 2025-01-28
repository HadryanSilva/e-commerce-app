package br.com.hadryan.ecommerce.product.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Product Name is required")
    private String name;
    private String description;
    @Positive(message = "Available Quantity should be positive")
    private Integer availableQuantity;
    @Positive(message = "Price should be positive")
    private BigDecimal price;
    @NotNull(message = "Category is required")
    private String category;

}
