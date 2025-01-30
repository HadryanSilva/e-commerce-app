package br.com.hadryan.ecommerce.notification.kafka.order;

import br.com.hadryan.ecommerce.notification.kafka.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
