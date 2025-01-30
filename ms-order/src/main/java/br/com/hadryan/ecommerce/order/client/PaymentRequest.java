package br.com.hadryan.ecommerce.order.client;

import br.com.hadryan.ecommerce.order.mapper.response.CustomerResponse;
import br.com.hadryan.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
