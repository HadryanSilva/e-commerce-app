package br.com.hadryan.ecommerce.customer.service;

import br.com.hadryan.ecommerce.customer.exception.CustomerNotFoundException;
import br.com.hadryan.ecommerce.customer.mapper.CustomerMapper;
import br.com.hadryan.ecommerce.customer.mapper.request.CustomerRequest;
import br.com.hadryan.ecommerce.customer.mapper.response.CustomerResponse;
import br.com.hadryan.ecommerce.customer.model.Customer;
import br.com.hadryan.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll(int page, int size) {
        log.info("Finding all customers");
        return repository.findAll(PageRequest.of(page, size))
                .map(customerMapper::customerToResponse)
                .stream()
                .toList();
    }

    public CustomerResponse findById(String id) {
        log.info("Finding customer by id {}", id);
        var customerFound = repository.findById(id).orElseThrow(() -> {
            log.error("Customer not found");
            return new CustomerNotFoundException("Customer with id " + id + " not found");
        });

        return customerMapper.customerToResponse(customerFound);
    }

    public Customer save(CustomerRequest request) {
        log.info("Saving customer {}", request.getFirstname());
        var customerToSave = customerMapper.requestToCustomer(request);
        return repository.save(customerToSave);
    }

    public void update(CustomerRequest request, String id) {
        log.info("Updating customer with id {}", id);
        var customerToUpdate = repository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(String.format("Customer with id %s not found", id)));
        validateFields(request, customerToUpdate);
        repository.save(customerToUpdate);
        log.info("Customer with id: {} updated successfully", customerToUpdate.getId());

    }

    private void validateFields(CustomerRequest request, Customer customer) {
        if (StringUtils.isNotBlank(request.getFirstname())) {
            customer.setFirstname(request.getFirstname());
        }
        if (StringUtils.isNotBlank(request.getLastname())) {
            customer.setLastname(request.getLastname());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
    }

}
