package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;
import ua.tqs.project.quickserve.entities.Address;

@Getter
@Setter
public class AddressDTO {
    String street;
    String city;
    String postalCode;
    String country;

    public AddressDTO(String street, String city, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
    }

    public AddressDTO() {
    }
}
