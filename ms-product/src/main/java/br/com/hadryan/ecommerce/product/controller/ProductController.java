package br.com.hadryan.ecommerce.product.controller;

import br.com.hadryan.ecommerce.product.mapper.request.ProductPurchaseRequest;
import br.com.hadryan.ecommerce.product.mapper.request.ProductRequest;
import br.com.hadryan.ecommerce.product.mapper.response.ProductResponse;
import br.com.hadryan.ecommerce.product.mapper.response.ProductPurchaseResponse;
import br.com.hadryan.ecommerce.product.service.ProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        var products = service.findAll(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<ProductResponse> findById(@RequestParam("id") Long id) {
        var productFound = service.findById(id);
        return ResponseEntity.ok(productFound);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        var productSaved = service.save(request);
        return ResponseEntity.created(URI.create("/api/v1/product?id=" + productSaved.getId()))
                .body(productSaved);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchase(@Valid @RequestBody List<ProductPurchaseRequest> request) {
        var productsPurchased = service.purchase(request);
        return ResponseEntity.ok(productsPurchased);
    }

}
