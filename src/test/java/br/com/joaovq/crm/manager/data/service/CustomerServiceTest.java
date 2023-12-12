package br.com.joaovq.crm.manager.data.service;

import br.com.joaovq.crm.manager.data.models.Customer;
import br.com.joaovq.crm.manager.data.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnListCustomers() {
//        given
        when(customerRepository.findAll((Pageable) any()))
                .thenReturn(new PageImpl<Customer>(new ArrayList<>()));
//        when
        var customers = customerService.listAllCustomers(Pageable.ofSize(2));
//        then
        Assertions.assertThat(customers.getContent()).isEmpty();
    }

    @Test
    void shouldCustomerReturnVoid() {
        //        given
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        //        when
        var customer = customerService.createCustomer(new Customer());
//        then
        Assertions.assertThat(customer).isNotNull();
    }
}
