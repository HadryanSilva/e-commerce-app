package br.com.hadryan.ecommerce.payment.service;

import br.com.hadryan.ecommerce.payment.kafka.NotificationProducer;
import br.com.hadryan.ecommerce.payment.kafka.PaymentNotificationRequest;
import br.com.hadryan.ecommerce.payment.mapper.PaymentMapper;
import br.com.hadryan.ecommerce.payment.mapper.request.PaymentRequest;
import br.com.hadryan.ecommerce.payment.mapper.response.PaymentResponse;
import br.com.hadryan.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public PaymentResponse createPayment(PaymentRequest request) {
        log.info("Creating payment");
        //TODO: Implement payment processing
        var paymentToSave = mapper.requestToModel(request);
        var savedPayment = repository.save(paymentToSave);
        log.info("Sending payment notification");
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstname(),
                        request.getCustomer().getLastname(),
                        request.getCustomer().getEmail()
                )
        );
        return mapper.modelToResponse(savedPayment);
    }

}
