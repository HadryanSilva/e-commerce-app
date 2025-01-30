package br.com.hadryan.ecommerce.notification.kafka.payment;

import br.com.hadryan.ecommerce.notification.kafka.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
