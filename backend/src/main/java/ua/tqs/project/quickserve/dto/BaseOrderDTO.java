package ua.tqs.project.quickserve.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.PickupMethod;

public class BaseOrderDTO {
    @JsonProperty("orderId")
    long orderId;

    @JsonProperty("scheduledTime")
    LocalDateTime scheduledTime;
    // Creation time is registered at the time of object instantiation
    // Total price will be calculated in the backend

    @JsonProperty("deliveryAddress")
    Address deliveryAddress;

    @JsonProperty("restaurantId")
    long restaurantId;

    @JsonProperty("userId")
    long userId;

    @JsonProperty("pickupMethod")
    PickupMethod pickupMethod;
    // Status is set to SCHEDULED by default

    public BaseOrderDTO() {
    }

    public BaseOrderDTO(long orderId, LocalDateTime scheduledTime, Address deliveryAddress, Long restaurantId, Long userId, PickupMethod pickupMethod) {
        this.orderId = orderId;
        this.scheduledTime = scheduledTime;
        this.deliveryAddress = deliveryAddress;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.pickupMethod = pickupMethod;
    }

    public BaseOrderDTO(Order order) {
        this.orderId = order.getId();
        this.scheduledTime = order.getScheduledTime();
        this.deliveryAddress = order.getDeliveryAddress();
        this.restaurantId = order.getRestaurant().getId();
        this.userId = order.getUser().getId();
        this.pickupMethod = order.getPickupMethod();
    }
}
