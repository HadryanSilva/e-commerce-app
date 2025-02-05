package br.com.hadryan.ecommerce.payment.kafka;

public record OrderStatusRequest(
        Long orderId,
        String orderReference,
        OrderStatus status
) {
}
