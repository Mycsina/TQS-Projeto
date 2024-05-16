package ua.tqs.project.quickserve.entities;

import java.util.*;

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
@Table(name="ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;
    // May not be the actual price of the ingredient, but the price of adding it to an item
    
    @Column(nullable = false)
    private boolean canBeChanged;
    // If the ingredient can be added or removed from an item

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Ingredient(String name, double price, boolean canBeChanged, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.canBeChanged = canBeChanged;
        this.restaurant = restaurant;
    }
}