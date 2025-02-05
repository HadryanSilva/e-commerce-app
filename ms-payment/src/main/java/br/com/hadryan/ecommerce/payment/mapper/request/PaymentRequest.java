package br.com.hadryan.ecommerce.payment.mapper.request;

import br.com.hadryan.ecommerce.payment.model.Customer;
import br.com.hadryan.ecommerce.payment.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private String orderReference;
    private Customer customer;
}
