package br.com.joaovq.crm.manager.domain.request;

import br.com.joaovq.crm.manager.data.models.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record CreateAddressRequest(
        @NotNull
        @NotBlank
        @Length(min = 1, max = 9)
        String cep,
        @NotNull
        @NotBlank
        String publicPlace,
        @NotNull
        @NotBlank
        String neighborhood,
        @NotNull
        @NotBlank
        String complement,
        String locality,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {
    public CreateAddressRequest {
    }

    public Address toEntity() {
        return new Address(
                UUID.randomUUID(),
                cep(),
                publicPlace(),
                complement(),
                neighborhood(),
                locality(),
                uf(),
                gia(),
                ibge(),
                ddd(),
                siafi()
        );
    }

}
