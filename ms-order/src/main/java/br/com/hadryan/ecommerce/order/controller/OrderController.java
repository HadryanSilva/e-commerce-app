package br.com.hadryan.ecommerce.order.controller;

import br.com.hadryan.ecommerce.order.mapper.request.OrderRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderResponse;
import br.com.hadryan.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponse>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        var orders = service.findAll(page, size);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<OrderResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        var response = service.createOrder(request);
        return ResponseEntity
                .created(URI.create("/api/v1/order/?id=" + response.getId()))
                .body(response);
    }

}
