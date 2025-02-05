package br.com.hadryan.ecommerce.order.kafka;

import br.com.hadryan.ecommerce.order.exception.BusinessException;
import br.com.hadryan.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentConsumer {

    private final OrderRepository repository;

    @KafkaListener(topics = "order-status-topic")
    public void consumeOrderStatusUpdate(OrderStatusRequest orderStatusRequest) {
        log.info("Consuming order status update: <{}>", orderStatusRequest);
        var order = repository.findById(orderStatusRequest.orderId())
                .orElseThrow(() -> new BusinessException("Order not found"));
        order.setStatus(orderStatusRequest.status());
        repository.save(order);
    }

}
