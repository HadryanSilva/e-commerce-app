package br.com.hadryan.ecommerce.order.controller;

import br.com.hadryan.ecommerce.order.mapper.request.OrderRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderResponse;
import br.com.hadryan.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        var response = service.createOrder(request);
        return ResponseEntity
                .created(URI.create("/api/v1/order/?id=" + response.getId()))
                .body(response);
    }

}
