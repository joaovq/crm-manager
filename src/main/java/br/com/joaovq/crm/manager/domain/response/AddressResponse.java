package br.com.joaovq.crm.manager.domain.response;

import br.com.joaovq.crm.manager.data.models.Address;

import java.util.UUID;

public record AddressResponse(
        UUID id,
        String cep,
        String publicPlace,
        String neighborhood,
        String complement,
        String locality,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {
    public static AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getCep(),
                address.getPublicPlace(),
                address.getNeighborhood(),
                address.getComplement(),
                address.getLocality(),
                address.getUf(),
                address.getIbge(),
                address.getGia(),
                address.getDdd(),
                address.getSiafi()
        );
    }
}
