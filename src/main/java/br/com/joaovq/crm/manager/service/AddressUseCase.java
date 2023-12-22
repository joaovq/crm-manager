package br.com.joaovq.crm.manager.service;

import br.com.joaovq.crm.manager.data.models.Address;

import java.util.UUID;

public interface AddressUseCase {
    Address createAddress(Address address);
    Address getAddressById(UUID id);
    void deleteAddress(UUID id);
}
