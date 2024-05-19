package ua.tqs.project.quickserve.dto;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.PickupMethod;

import java.time.LocalDateTime;

public class OrderDTO {
    LocalDateTime scheduledTime;
    // Creation time is registered at the time of object instantiation
    // Total price will be calculated in the backend
    Address deliveryAddress;
    Long restaurantId;
    Long userId;
    PickupMethod pickupMethod;
    // Status is set to SCHEDULED by default
}
