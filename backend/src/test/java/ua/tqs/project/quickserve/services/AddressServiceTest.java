package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.repositories.AddressRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AddressServiceTest {
    protected static final Logger logger = LogManager.getLogger(AddressServiceTest.class);

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        Address address1 = new Address(1L,"Rua do Amial", "Porto", "4200-055", "Portugal");
        Address address2 = new Address(1L,"Rua do Abc", "Viseu", "3323-231", "Portugal", "State"); address2.setId(2L);
        Address address3 = new Address("Rua do Xyz", "Lisboa", "1234-567", "Portugal"); address3.setId(3L);

        List<Address> allAddresss = Arrays.asList(address1, address2, address3);

        Mockito.when(addressRepository.findById(address1.getId())).thenReturn(Optional.of(address1));
        Mockito.when(addressRepository.findById(address2.getId())).thenReturn(Optional.of(address2));
        Mockito.when(addressRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(addressRepository.findAll()).thenReturn(allAddresss);

        Mockito.when(addressRepository.save(address1)).thenReturn(address1);
    }
    
    @Test
    void whenValidIdthenAddressShouldBeFound() {
        Address found = addressService.getAddressById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenAddressShouldNotBeFound() {
        Address found = addressService.getAddressById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveAddressthenAddressShouldBeReturned() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal"); address.setId(1L);
        Mockito.when(addressRepository.save(address)).thenReturn(address);
        
        Address savedAddress = addressService.save(address);
        assertThat(savedAddress).isNotNull();
    }

    @Test
    void whenDeleteAddressthenAddressShouldBeDeleted() {
        Long addressId = 1L;

        // Setup mock behavior
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(new Address()));
            
        addressService.deleteAddressById(addressId);
        Mockito.verify(addressRepository, times(1)).deleteById(addressId);
    }

    @Test
    void whenGetAddressByPostalCodethenAddressShouldBeReturned() {
        String postalCode = "4200-055";
        Address address = new Address("Rua do Amial", "Porto", postalCode, "Portugal");
        when(addressRepository.findByPostalCode(postalCode)).thenReturn(address);
        
        Address found = addressService.getAddressByPostalCode(postalCode);
        assertThat(found.getPostalCode()).isEqualTo(postalCode);
    }

}
