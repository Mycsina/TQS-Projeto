package ua.tqs.project.quickserve.entities;

import java.util.*;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int phone;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;
    // Client Side

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = true)
    private Restaurant restaurant;
    // Manager Side
    
    public User(String name, String password, RoleEnum role, String email, int phone) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public User(String name, String password, RoleEnum role, String email, int phone, Address address) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(String name, String password, RoleEnum role, String email, int phone, Restaurant restaurant) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.restaurant = restaurant;
    }
}