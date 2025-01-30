package br.com.hadryan.ecommerce.payment.mapper;

import br.com.hadryan.ecommerce.payment.mapper.request.PaymentRequest;
import br.com.hadryan.ecommerce.payment.mapper.response.PaymentResponse;
import br.com.hadryan.ecommerce.payment.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment requestToModel(PaymentRequest request);

    PaymentResponse modelToResponse(Payment payment);

}
