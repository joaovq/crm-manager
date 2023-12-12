package br.com.joaovq.crm.manager.domain.request;

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
        String cpf
) {
    public Customer toEntity() {
        return new Customer(
                null,
                this.firstName,
                this.lastName,
                this.cpf
        );
    }
}
