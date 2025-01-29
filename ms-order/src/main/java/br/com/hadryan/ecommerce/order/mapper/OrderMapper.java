package br.com.hadryan.ecommerce.order.mapper;

import br.com.hadryan.ecommerce.order.mapper.request.OrderRequest;
import br.com.hadryan.ecommerce.order.mapper.response.OrderResponse;
import br.com.hadryan.ecommerce.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order requestToOrder(OrderRequest request);

    OrderResponse orderToResponse(Order order);

}
