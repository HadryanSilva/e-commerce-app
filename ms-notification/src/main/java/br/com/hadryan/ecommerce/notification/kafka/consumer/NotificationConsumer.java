package br.com.hadryan.ecommerce.notification.kafka.consumer;

import br.com.hadryan.ecommerce.notification.kafka.order.OrderConfirmation;
import br.com.hadryan.ecommerce.notification.kafka.payment.PaymentConfirmation;
import br.com.hadryan.ecommerce.notification.model.Notification;
import br.com.hadryan.ecommerce.notification.repository.NotificationRepository;
import br.com.hadryan.ecommerce.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.hadryan.ecommerce.notification.model.NotificationType.ORDER_CONFIRMATION;
import static br.com.hadryan.ecommerce.notification.model.NotificationType.PAYMENT_CONFIRMATION;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation message) throws MessagingException {
        log.info("Consuming order confirmation notification: {}", message);
        var notification = Notification.builder()
                .date(LocalDateTime.now())
                .type(ORDER_CONFIRMATION)
                .orderConfirmation(message)
                .build();
        repository.save(notification);

        var customerName = message.customer().firstname() + " " + message.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                message.customer().email(),
                customerName,
                message.totalAmount(),
                message.orderReference(),
                message.products()
        );
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation message) throws MessagingException {
        log.info("Consuming payment success notification: {}", message);
        var notification = Notification.builder()
                .date(LocalDateTime.now())
                .type(PAYMENT_CONFIRMATION)
                .paymentConfirmation(message)
                .build();
        repository.save(notification);

        var customerName = message.customerFirstName() + " " + message.customerLastName();
        emailService.sendPaymentSuccessEmail(
                message.customerEmail(),
                customerName,
                message.amount(),
                message.orderReference()
        );
    }

}
