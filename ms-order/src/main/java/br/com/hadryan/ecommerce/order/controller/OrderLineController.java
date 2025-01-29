package br.com.hadryan.ecommerce.order.controller;

import br.com.hadryan.ecommerce.order.mapper.response.OrderLineResponse;
import br.com.hadryan.ecommerce.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order-line")
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@RequestParam("orderId") Long orderId) {
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }

}
