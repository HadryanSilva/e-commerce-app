package br.com.hadryan.ecommerce.product.mapper.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPurchaseRequest {
    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;
}
