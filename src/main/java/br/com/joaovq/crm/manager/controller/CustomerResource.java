package br.com.joaovq.crm.manager.controller;

import br.com.joaovq.crm.manager.data.models.Customer;
import br.com.joaovq.crm.manager.data.service.CustomerUseCase;
import br.com.joaovq.crm.manager.domain.request.CustomerCreateRequest;
import br.com.joaovq.crm.manager.domain.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "v1/customer")
public class CustomerResource {

    @Autowired
    private CustomerUseCase customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable) {
        return ResponseEntity.ok(customerService.listAllCustomers(pageable));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        Customer customer = customerService.createCustomer(customerCreateRequest.toEntity());
        return ResponseEntity.ok(CustomerResponse.toResponse(customer));
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(CustomerResponse.toResponse(customer));
    }
}
