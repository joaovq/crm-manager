package br.com.joaovq.crm.manager.domain.request;

import br.com.joaovq.crm.manager.data.models.Address;
import br.com.joaovq.crm.manager.data.models.Customer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerCreateRequest(
        @NotEmpty
        @NotNull
        String firstName,
        @NotEmpty
        @NotNull
        String lastName,
        @NotEmpty
        @NotNull
        @CPF
        String cpf,
        @NotNull
        CreateAddressRequest address
) {
    public Customer toEntity(Address address) {
        return new Customer(
                null,
                this.firstName,
                this.lastName,
                this.cpf,
                address
        );
    }
}
