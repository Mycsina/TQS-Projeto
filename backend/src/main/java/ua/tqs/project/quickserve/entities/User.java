package ua.tqs.project.quickserve.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "userEntity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private int phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    // Client Side

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
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

}