package ua.tqs.project.quickserve.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;
    // May not be the actual price of the ingredient, but the price of adding it to an item

    @Column(nullable = false)
    private boolean canBeChanged;
    // If the ingredient can be added or removed from an item

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Ingredient(String name, double price, boolean canBeChanged, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.canBeChanged = canBeChanged;
        this.restaurant = restaurant;
    }

    public Ingredient(String name, double price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.canBeChanged = false;
        this.restaurant = restaurant;
    }
}