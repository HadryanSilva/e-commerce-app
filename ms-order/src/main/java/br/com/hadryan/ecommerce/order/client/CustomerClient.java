package br.com.hadryan.ecommerce.order.client;

import br.com.hadryan.ecommerce.order.mapper.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(
        name = "ms-customer",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/api/v1/customer")
    Optional<CustomerResponse> findCustomerById(@RequestParam("id") String customerId);

}
