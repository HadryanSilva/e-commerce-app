package br.com.hadryan.ecommerce.order.mapper.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderLineRequest {

    private Long orderId;
    private Long productId;
    private Integer quantity;

}
