package br.com.joaovq.crm.manager.controller;

import br.com.joaovq.crm.manager.data.models.Customer;
import br.com.joaovq.crm.manager.data.service.CustomerUseCase;
import br.com.joaovq.crm.manager.domain.request.CustomerCreateRequest;
import br.com.joaovq.crm.manager.domain.response.CustomerResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/customer")
public class CustomerResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CustomerUseCase customerService;

    @GetMapping(value = "search")
    public ResponseEntity<Collection<CustomerResponse>> search(@RequestParam(name = "query", defaultValue = "") String name) {
        var customers = customerService.searchCustomerByName(name);
        return ResponseEntity.ok(customers.stream().map(CustomerResponse::toResponse).toList());
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(customerService.listAllCustomers(pageable));
    }

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        Customer customer = customerService.createCustomer(customerCreateRequest.toEntity());
        return ResponseEntity.ok(CustomerResponse.toResponse(customer));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(CustomerResponse.toResponse(customer));
    }

}
