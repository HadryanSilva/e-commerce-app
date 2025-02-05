package br.com.hadryan.ecommerce.order.service;

import br.com.hadryan.ecommerce.order.client.CustomerClient;
import br.com.hadryan.ecommerce.order.client.PaymentClient;
import br.com.hadryan.ecommerce.order.client.PaymentRequest;
import br.com.hadryan.ecommerce.order.client.ProductClient;
import br.com.hadryan.ecommerce.order.exception.BusinessException;
import br.com.hadryan.ecommerce.order.kafka.NotificationProducer;
import br.com.hadryan.ecommerce.order.kafka.OrderConfirmation;
import br.com.hadryan.ecommerce.order.mapper.OrderMapper;
import br.com.hadryan.ecommerce.order.mapper.request.OrderLineRequest;
import br.com.hadryan.ecommerce.order.mapper.request.OrderRequest;
import br.com.hadryan.ecommerce.order.mapper.request.PurchaseRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderResponse;
import br.com.hadryan.ecommerce.order.model.Order;
import br.com.hadryan.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final NotificationProducer notificationProducer;
    private final PaymentClient paymentClient;

    public List<OrderResponse> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(orderMapper::orderToResponse)
                .toList();
    }

    public OrderResponse findById(Long id) {
        return repository.findById(id)
                .map(orderMapper::orderToResponse)
                .orElseThrow(() -> new BusinessException(String.format("Order with id %s not found", id)));
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() ->
                        new BusinessException(String.format("Customer with id %s not found", request.getCustomerId())));
        var purchases = productClient.purchaseProducts(request.getProducts());
        var order = repository.save(orderMapper.requestToOrder(request));
        saveOrderLines(request, order);

        paymentClient.requestOrderPayment(
                new PaymentRequest(
                        request.getAmount(),
                        request.getPaymentMethod(),
                        order.getId(),
                        order.getReference(),
                        customer
                )
        );

        notificationProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        customer,
                        purchases
                )
        );

        return orderMapper.orderToResponse(order);
    }

    private void saveOrderLines(OrderRequest request, Order order) {
        for (PurchaseRequest purchaseRequest : request.getProducts()) {
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .orderId(order.getId())
                            .productId(purchaseRequest.getProductId())
                            .quantity(purchaseRequest.getQuantity())
                            .build()
            );
        }
    }

}
