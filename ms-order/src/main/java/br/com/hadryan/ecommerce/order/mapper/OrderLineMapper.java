package br.com.hadryan.ecommerce.order.mapper;

import br.com.hadryan.ecommerce.order.mapper.request.OrderLineRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderLineResponse;
import br.com.hadryan.ecommerce.order.model.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order.id", source = "orderId")
    OrderLine requestToOrderLine(OrderLineRequest request);

    OrderLineResponse orderLineToResponse(OrderLine orderLine);

}
