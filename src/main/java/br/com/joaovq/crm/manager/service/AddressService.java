package br.com.joaovq.crm.manager.service;

import br.com.joaovq.crm.manager.data.models.Address;
import br.com.joaovq.crm.manager.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService implements AddressUseCase {
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(UUID id) {
        return addressRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteAddress(UUID id) {
        boolean customerExists = addressRepository.existsById(id);
        if (customerExists) {
            addressRepository.deleteById(id);
            return;
        }
        throw new RuntimeException();
    }
}
