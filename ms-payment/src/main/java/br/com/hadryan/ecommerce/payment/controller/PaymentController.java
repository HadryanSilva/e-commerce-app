package br.com.hadryan.ecommerce.payment.controller;

import br.com.hadryan.ecommerce.payment.mapper.request.PaymentRequest;
import br.com.hadryan.ecommerce.payment.mapper.response.PaymentResponse;
import br.com.hadryan.ecommerce.payment.service.PaymentService;
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
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
       var response = service.createPayment(request);
       return ResponseEntity
               .created(URI.create("/api/v1/payment/" + response.getId()))
               .body(response);
    }

}
