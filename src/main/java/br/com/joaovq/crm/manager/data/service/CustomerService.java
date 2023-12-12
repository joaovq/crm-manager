package br.com.joaovq.crm.manager.data.service;

import br.com.joaovq.crm.manager.core.exception.CustomerNotFoundException;
import br.com.joaovq.crm.manager.data.repository.CustomerRepository;
import br.com.joaovq.crm.manager.data.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CustomerUseCase {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> listAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(CustomerNotFoundException::new);
    }
}
