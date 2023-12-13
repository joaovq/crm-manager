package br.com.joaovq.crm.manager.data.service;

import br.com.joaovq.crm.manager.data.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface CustomerUseCase {
    Page<Customer> listAllCustomers(Pageable pageable);
    Customer createCustomer(Customer customer);
    Customer getCustomerById(UUID id);
    Collection<Customer> searchCustomerByName(String name);
    void deleteCustomer(UUID id);
}
