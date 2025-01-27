package br.com.hadryan.ecommerce.customer.mapper;

import br.com.hadryan.ecommerce.customer.mapper.request.CustomerRequest;
import br.com.hadryan.ecommerce.customer.mapper.response.CustomerResponse;
import br.com.hadryan.ecommerce.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer requestToCustomer(CustomerRequest request);

    CustomerResponse customerToResponse(Customer customer);
}
