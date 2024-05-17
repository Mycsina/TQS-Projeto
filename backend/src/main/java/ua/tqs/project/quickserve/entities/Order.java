package ua.tqs.project.quickserve.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime scheduledTime;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAtTime = LocalDateTime.now();

    @Column(nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PickupMethod pickupMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Order(LocalDateTime scheduledTime, double totalPrice, Restaurant restaurant, User user, PickupMethod pickupMethod) {
        this.scheduledTime = scheduledTime;
        this.totalPrice = totalPrice;
        this.restaurant = restaurant;
        this.user = user;
        this.pickupMethod = pickupMethod;
    }

    public Order(LocalDateTime scheduledTime, double totalPrice, Address deliveryAddress, Restaurant restaurant, User user, PickupMethod pickupMethod) {
        this.scheduledTime = scheduledTime;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.restaurant = restaurant;
        this.user = user;
        this.pickupMethod = pickupMethod;
    }

    // For data initialization purposes
    public Order (LocalDateTime scheduledTime, double totalPrice, Address deliveryAddress, Restaurant restaurant, User user, PickupMethod pickupMethod, Status status) {
        this.scheduledTime = scheduledTime;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.restaurant = restaurant;
        this.user = user;
        this.pickupMethod = pickupMethod;
        this.status = status;
    }
}