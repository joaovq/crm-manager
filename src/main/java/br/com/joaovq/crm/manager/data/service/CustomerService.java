package br.com.joaovq.crm.manager.data.service;

import br.com.joaovq.crm.manager.core.exception.CustomerNotFoundException;
import br.com.joaovq.crm.manager.data.models.Customer;
import br.com.joaovq.crm.manager.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    @Override
    public Collection<Customer> searchCustomerByName(String name) {
        return customerRepository.searchByQuery(name);
    }

    @Override
    public void deleteCustomer(UUID id) {
        boolean customerExists = customerRepository.existsById(id);
        if (customerExists) {
            customerRepository.deleteById(id);
            return;
        }
        throw new CustomerNotFoundException();
    }
}
