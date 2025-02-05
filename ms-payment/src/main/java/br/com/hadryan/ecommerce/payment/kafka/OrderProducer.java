package br.com.hadryan.ecommerce.payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderStatusRequest> template;

    public void sendOrderStatusUpdate(OrderStatusRequest orderStatusRequest) {
        log.info("Sending order status");
        template.send("order-status-topic", orderStatusRequest);
    }

}
