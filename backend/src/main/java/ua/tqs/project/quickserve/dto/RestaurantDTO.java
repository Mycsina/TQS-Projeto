package ua.tqs.project.quickserve.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.State;

@Getter
@Setter
public class RestaurantDTO {
    String name;
    String description;
    int phoneNumber;
    LocalTime openingTime;
    LocalTime closingTime;
    State state;
    AddressDTO address;
    long managerId;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.phoneNumber = restaurant.getPhone();
        this.openingTime = restaurant.getOpeningTime();
        this.closingTime = restaurant.getClosingTime();
        this.state = restaurant.getState();
        this.address = new AddressDTO(restaurant.getAddress());
        this.managerId = restaurant.getManager().getId();
    }

}
