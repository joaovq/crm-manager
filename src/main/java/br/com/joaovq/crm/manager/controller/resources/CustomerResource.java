package br.com.joaovq.crm.manager.controller.resources;

import br.com.joaovq.crm.manager.data.models.Address;
import br.com.joaovq.crm.manager.data.models.Customer;
import br.com.joaovq.crm.manager.service.address.AddressUseCase;
import br.com.joaovq.crm.manager.service.customer.CustomerUseCase;
import br.com.joaovq.crm.manager.controller.request.CustomerCreateRequest;
import br.com.joaovq.crm.manager.controller.response.CustomerResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/customer")
public class CustomerResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CustomerUseCase customerService;
    @Autowired
    private AddressUseCase addressService;

    @GetMapping(value = "search")
    public ResponseEntity<Collection<CustomerResponse>> search(@RequestParam(name = "query", defaultValue = "") String name) {
        var customers = customerService.searchCustomerByName(name);
        return ResponseEntity.ok(customers.stream().map(CustomerResponse::toResponse).toList());
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(customerService.listAllCustomers(pageable).map(CustomerResponse::toResponse));
    }

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        Address address = addressService.createAddress(customerCreateRequest.address().toEntity());
        Customer customer = customerService.createCustomer(customerCreateRequest.toEntity(address));
        return ResponseEntity.created(URI.create("v1/customer" + "/" + customer.getId())).build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(CustomerResponse.toResponse(customer));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
