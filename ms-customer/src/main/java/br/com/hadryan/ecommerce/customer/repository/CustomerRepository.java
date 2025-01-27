package br.com.hadryan.ecommerce.customer.repository;

import br.com.hadryan.ecommerce.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Page<Customer> findAll(Pageable pageable);
}
