package br.com.hadryan.ecommerce.order.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest {

    @NotBlank(message = "Product id is mandatory")
    private Long productId;
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

}
