package br.com.hadryan.ecommerce.order.mapper.response;

import br.com.hadryan.ecommerce.order.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerId;

}
