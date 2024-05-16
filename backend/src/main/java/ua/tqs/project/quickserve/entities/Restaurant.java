package ua.tqs.project.quickserve.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    public Restaurant(String name, String description, int phone, State state, Address address, Menu menu) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.state = state;
        this.address = address;
        this.menu = menu;
    }

    public void setTimes(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
}