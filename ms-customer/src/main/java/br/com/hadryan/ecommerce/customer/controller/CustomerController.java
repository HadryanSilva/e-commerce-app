package br.com.hadryan.ecommerce.customer.controller;

import br.com.hadryan.ecommerce.customer.mapper.request.CustomerRequest;
import br.com.hadryan.ecommerce.customer.mapper.response.CustomerResponse;
import br.com.hadryan.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> findAll(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        var customers = service.findAll(page, size);
        return ResponseEntity.ok(customers);
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> findById(@RequestParam("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkCustomerExists(@RequestParam String id) {
        return ResponseEntity.ok(service.checkCustomerExists(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerRequest request) {
        var response = service.save(request);
        return ResponseEntity.created(URI.create("/customers/?id=" + response.getId())).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerRequest request,
                                                   @RequestParam("id") String id) {
        service.update(request, id);
        return ResponseEntity.noContent().build();
    }

}
