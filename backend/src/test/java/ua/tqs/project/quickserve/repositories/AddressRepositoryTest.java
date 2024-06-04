package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ua.tqs.project.quickserve.dto.AddressDTO;
import ua.tqs.project.quickserve.entities.Address;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AddressRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(AddressRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Find Address By Id")
    void whenFindAddressByIdthenReturnAddress() {
        Address address = new Address("Rua do Zé", "Porto", "4000-000", "Portugal");
        address.setState("State");

        entityManager.persistAndFlush(address); //ensure data is persisted at this point

        long id = address.getId();
        Address found = addressRepository.findById(id).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getCity()).isEqualTo("Porto");
        assertThat(found.getState()).isEqualTo("State");
    }

    @Test
    void whenFindAllAddressesthenReturnAddresses() {
        Address address1 = new Address("Rua do Zé", "Porto", "4000-000", "Portugal");
        Address address2 = new Address("Random", "Viseu", "3000-000", "Portugal");
        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.flush();
        
        List<Address> found = addressRepository.findAll();
        assertThat(found).hasSize(2);
        assertThat(found.get(0).getStreet()).isEqualTo("Rua do Zé");
        assertThat(found.get(1).getPostalCode()).isEqualTo("3000-000");

    }

    @Test 
    void saveAddress() {
        Address address = new Address("abc", "abc", "2000-000", "abc");
        long id = address.getId();

        addressRepository.save(address);
        assertThat(addressRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteAddress() {
        Address address = new Address("Rua do Zé", "Porto", "4000-000", "Portugal");
        entityManager.persistAndFlush(address);
        long id = address.getId();

        assertThat(addressRepository.findById(id)).isNotNull();
        assertThat(addressRepository.findById(id).get().getCountry()).isEqualTo("Portugal");
        addressRepository.delete(address);
        assertThat(addressRepository.findById(id)).isEmpty();
    }

    @Test
    void whenFindAddressByPostalCodethenReturnAddress() {
        Address address = new Address("Rua do Zé", "Porto", "4000-000", "Portugal");
        entityManager.persistAndFlush(address);

        Address found = addressRepository.findByPostalCode("4000-000");
        assertThat(found).isNotNull();
        assertThat(found.getCity()).isEqualTo("Porto");
    }

    @Test
    void addressDTOConstructor() {
        AddressDTO addressDTO = new AddressDTO("Rua do Zé", "Porto", "4000-000", "Portugal");
        Address address = new Address(addressDTO);

        assertThat(address.getStreet()).isEqualTo("Rua do Zé");
        assertThat(address.getCity()).isEqualTo("Porto");
    }
}
