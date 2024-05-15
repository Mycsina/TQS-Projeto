package ua.tqs.project.quickserve.entities;

import java.util.*;
import java.time.LocalTime;

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
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime openingTime;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime closingTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}