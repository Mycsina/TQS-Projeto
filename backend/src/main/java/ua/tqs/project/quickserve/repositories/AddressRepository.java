package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByPostalCode(String postalCode);
}