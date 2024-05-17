package ua.tqs.project.quickserve.entities;

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
@Table(name="itemIngredient")
public class ItemIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int ingredientQuantity;

    @Column(nullable = false)
    private boolean isDefault;
    // Whether the item ingredient has a default quantity or not

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = true)
    private Item item;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderItem_id", nullable = true)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public ItemIngredient(int ingredientQuantity, boolean isDefault, Item item, Ingredient ingredient) {
        this.ingredientQuantity = ingredientQuantity;
        this.isDefault = isDefault;
        this.item = item;
        this.ingredient = ingredient;
    }

    public ItemIngredient(int ingredientQuantity, boolean isDefault, OrderItem orderItem, Ingredient ingredient) {
        this.ingredientQuantity = ingredientQuantity;
        this.isDefault = isDefault;
        this.orderItem = orderItem;
        this.ingredient = ingredient;
    }
}