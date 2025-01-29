package br.com.hadryan.ecommerce.order.service;

import br.com.hadryan.ecommerce.order.mapper.OrderLineMapper;
import br.com.hadryan.ecommerce.order.mapper.request.OrderLineRequest;
import br.com.hadryan.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public void saveOrderLine(OrderLineRequest request) {
        log.info("Saving order line");
        var orderLine = mapper.requestToOrderLine(request);
        repository.save(orderLine);
    }

}
