package br.com.hadryan.ecommerce.product.service;

import br.com.hadryan.ecommerce.product.mapper.ProductMapper;
import br.com.hadryan.ecommerce.product.mapper.request.ProductPurchaseRequest;
import br.com.hadryan.ecommerce.product.mapper.request.ProductRequest;
import br.com.hadryan.ecommerce.product.mapper.response.ProductPurchaseResponse;
import br.com.hadryan.ecommerce.product.mapper.response.ProductResponse;
import br.com.hadryan.ecommerce.product.model.Category;
import br.com.hadryan.ecommerce.product.model.Product;
import br.com.hadryan.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> findAll(int page, int size) {
        log.info("Finding all products");
        return productRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(productMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id)
                .map(productMapper::modelToResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse save(ProductRequest request) {
        log.info("Saving product: {}", request.getName());
        var productToSave = productMapper.requestToModel(request);
        productToSave.setCategory(Category.valueOf(request.getCategory()));
        var productSaved = productRepository.save(productToSave);
        return productMapper.modelToResponse(productSaved);
    }

    @Transactional
    public List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        var products = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != products.size()) {
            throw new RuntimeException("One or more products not found");
        }

        var storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        return executePurchase(storedRequest, products);
    }

    private List<ProductPurchaseResponse> executePurchase(List<ProductPurchaseRequest> request, List<Product> products) {
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < request.size(); i++) {
            var product = products.get(i);
            var productRequest = request.get(i);
            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new RuntimeException("Product " + product.getName() + " has insufficient quantity");
            }
            product.setAvailableQuantity(product.getAvailableQuantity() - productRequest.getQuantity());
            log.info("Updating available stock for product: {}", product.getName());
            productRepository.save(product);
            purchasedProducts.add(productMapper.modelToPurchaseResponse(product, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }

}
