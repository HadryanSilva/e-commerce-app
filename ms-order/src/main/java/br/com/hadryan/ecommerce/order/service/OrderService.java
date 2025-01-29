package br.com.hadryan.ecommerce.order.service;

import br.com.hadryan.ecommerce.order.client.CustomerClient;
import br.com.hadryan.ecommerce.order.client.ProductClient;
import br.com.hadryan.ecommerce.order.mapper.OrderMapper;
import br.com.hadryan.ecommerce.order.mapper.request.OrderLineRequest;
import br.com.hadryan.ecommerce.order.mapper.request.OrderRequest;
import br.com.hadryan.ecommerce.order.mapper.request.PurchaseRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderResponse;
import br.com.hadryan.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        productClient.purchaseProducts(request.getProducts());
        var order = repository.save(orderMapper.requestToOrder(request));
        saveOrderLines(request);

        //TODO: Start payment process

        //TODO: Send order confirmation notification

        return orderMapper.orderToResponse(order);
    }

    private void saveOrderLines(OrderRequest request) {
        for (PurchaseRequest purchaseRequest : request.getProducts()) {
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .productId(purchaseRequest.getProductId())
                            .quantity(purchaseRequest.getQuantity())
                            .build()
            );
        }
    }

}
