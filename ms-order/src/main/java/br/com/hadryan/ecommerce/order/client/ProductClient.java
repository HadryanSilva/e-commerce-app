package br.com.hadryan.ecommerce.order.client;

import br.com.hadryan.ecommerce.order.mapper.request.PurchaseRequest;
import br.com.hadryan.ecommerce.order.mapper.response.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<PurchaseRequest>> entity = new HttpEntity<>(request, headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                entity,
                responseType
        );
        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Error purchasing products");
        }
        return response.getBody();
    }
}
