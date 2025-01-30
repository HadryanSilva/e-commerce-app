package br.com.hadryan.ecommerce.notification.model;

import br.com.hadryan.ecommerce.notification.kafka.order.OrderConfirmation;
import br.com.hadryan.ecommerce.notification.kafka.PaymentConfirmation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime date;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
