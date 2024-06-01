package ua.tqs.project.quickserve.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Address;

@Getter
@Setter
public class RestaurantDTO {
    long restaurantId;
    String name;
    String description;
    int phoneNumber;
    LocalTime openingTime;
    LocalTime closingTime;
    String state;
    Address address;
    long managerId;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Restaurant restaurant) {
        this.restaurantId = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.phoneNumber = restaurant.getPhone();
        this.openingTime = restaurant.getOpeningTime();
        this.closingTime = restaurant.getClosingTime();
        this.state = restaurant.getState().toString();
        this.address = restaurant.getAddress();
        this.managerId = restaurant.getManager().getId();
    }

}
