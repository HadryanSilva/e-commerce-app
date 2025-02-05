package br.com.hadryan.ecommerce.order.kafka;

import br.com.hadryan.ecommerce.order.model.OrderStatus;

public record OrderStatusRequest(
        Long orderId,
        String orderReference,
        OrderStatus status
) {
}
