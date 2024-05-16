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
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    // Name of the image file
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Item(String name, String description, String image, double price, Restaurant restaurant, Category category) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.restaurant = restaurant;
        this.category = category;
    }
}