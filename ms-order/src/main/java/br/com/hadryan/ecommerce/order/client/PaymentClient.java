package br.com.hadryan.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-payment",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    PaymentResponse requestOrderPayment(@RequestBody PaymentRequest request);

}
