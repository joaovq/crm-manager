package br.com.joaovq.crm.manager.domain.response;

import br.com.joaovq.crm.manager.data.models.Customer;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String firstName,
        String lastName,
        String cpf
) {
    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCpf()
        );
    }
}
