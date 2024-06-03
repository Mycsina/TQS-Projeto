package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.repositories.AddressRepository;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository repository;

    public Address save(Address address) {
        return repository.save(address);
    }

    public Address getAddressById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Address getAddressByPostalCode(String postalCode) {
        return repository.findByPostalCode(postalCode);
    }

    public void deleteAddressById(long id) {
        repository.deleteById(id);
    }
}
