package br.com.hadryan.ecommerce.order.mapper.request;

import br.com.hadryan.ecommerce.order.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class OrderRequest {

    private Long id;
    private String reference;
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
    @NotNull(message = "Payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotBlank(message = "Customer should be present")
    private String customerId;
    @NotEmpty(message = "At least one product should be present")
    private List<PurchaseRequest> products;

}
