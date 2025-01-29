package br.com.hadryan.ecommerce.order.kafka;

import br.com.hadryan.ecommerce.order.mapper.response.CustomerResponse;
import br.com.hadryan.ecommerce.order.mapper.response.PurchaseResponse;
import br.com.hadryan.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> purchases
) {
}
