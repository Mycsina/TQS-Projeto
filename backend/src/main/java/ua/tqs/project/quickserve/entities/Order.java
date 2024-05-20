package ua.tqs.project.quickserve.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderEntity")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PickupMethod pickupMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    // For data initialization purposes
    public Order(LocalDateTime scheduledTime, double totalPrice, Address deliveryAddress, Restaurant restaurant, User user, PickupMethod pickupMethod, Status status) {
        this.scheduledTime = scheduledTime;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.restaurant = restaurant;
        this.user = user;
        this.pickupMethod = pickupMethod;
        this.status = status;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Order order = (Order) o;
        return false;
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}