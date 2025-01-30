package br.com.hadryan.ecommerce.payment.mapper.response;

import br.com.hadryan.ecommerce.payment.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponse {

    private Long id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
