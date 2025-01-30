package br.com.hadryan.ecommerce.order.client;

import br.com.hadryan.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
