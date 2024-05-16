package ua.tqs.project.quickserve.services; 

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

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

    public void deleteAddressById(long id) {
        repository.deleteById(id);
    }
}
