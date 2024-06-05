package ua.tqs.project.quickserve;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.tqs.project.quickserve.entities.*;
import ua.tqs.project.quickserve.repositories.*;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemIngredientRepository itemIngredientRepository;
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            return;
        }
        
        String country = "Portugal";

        // Restaurant 1
        Address a1 = new Address("Rua do Amial", "Porto", "4200-055", country);
        User u2 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant r1 = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123122123, State.OPEN, a1, u2);
        r1.setTimes(LocalTime.of(10, 0, 0), LocalTime.of(4, 0, 0));
        Menu m1 = new Menu(r1);

        // Restaurant 2
        User u3 = new User("Burger Kings's Manager", "1234", RoleEnum.MANAGER, "bk.pt", 123123127);
        Address a3 = new Address("Rua do Abc", "Aveiro", "4220-035", country);
        Restaurant r2 = new Restaurant("Burger King", "Number 2 in the fast food industry!", 123123423, State.OPEN, a3, u3);
        r2.setTimes(LocalTime.of(10, 0, 0), LocalTime.of(4, 0, 0));
        Menu m2 = new Menu(r2);

        // Category
        Category c1 = new Category("Burgers", m1);
        Category c2 = new Category("Burgers", m2);

        // Items
        Item i1 = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, r1, c1);
        Item i2 = new Item("Whopper", "The most famous burger in the world!", "./images/whopperpic", 5.0, r2, c2);

        // Ingredients
        Ingredient ing1 = new Ingredient("Bread", 0.5, false, r1);
        Ingredient ing2 = new Ingredient("Burger", 1.0, true, r1);
        Ingredient ing3 = new Ingredient("Cheddar Cheese", 0.5, true, r1);
        Ingredient ing4 = new Ingredient("Lettuce", 0.5, true, r1);
        Ingredient ing5 = new Ingredient("Pickle", 0.5, true, r1);
        Ingredient ing6 = new Ingredient("Onion", 0.5, true, r1);
        Ingredient ing7 = new Ingredient("Big Mac Sauce", 0.5, true, r1);

        // ItemIngredients
        ItemIngredient ii1 = new ItemIngredient(2, true, i1, ing1);
        ItemIngredient ii2 = new ItemIngredient(2, true, i1, ing2);
        ItemIngredient ii3 = new ItemIngredient(1, true, i1, ing3);
        ItemIngredient ii4 = new ItemIngredient(1, true, i1, ing4);
        ItemIngredient ii5 = new ItemIngredient(1, true, i1, ing5);
        ItemIngredient ii6 = new ItemIngredient(1, true, i1, ing6);
        ItemIngredient ii7 = new ItemIngredient(1, true, i1, ing7);

        ItemIngredient ii8 = new ItemIngredient(2, true, i2, ing1);
        ItemIngredient ii9 = new ItemIngredient(2, true, i2, ing2);
        ItemIngredient ii10 = new ItemIngredient(1, true, i2, ing3);
        ItemIngredient ii11 = new ItemIngredient(1, true, i2, ing4);
        ItemIngredient ii12 = new ItemIngredient(1, true, i2, ing5);
        ItemIngredient ii13 = new ItemIngredient(1, true, i2, ing6);

        // Users
        Address a2 = new Address("Casa do Joao", "Viseu", "4250-055", country);
        User u1 = new User("Joao", "1234", RoleEnum.CLIENT, "joao@joao.pt", 123123129, a2);

        User u4 = new User("KFC Manager", "abcd", RoleEnum.MANAGER, "kfc.pt", 122333211);

        // Order
        Order o1 = new Order(LocalDateTime.now(), 5.0, a2, r1, u1, PickupMethod.DELIVERY, Status.IN_MAKING);

        // OrderItem
        OrderItem oi1 = new OrderItem(5, i1, o1);

        // Save
        addressRepository.save(a1);
        addressRepository.save(a2);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        addressRepository.save(a1);
        addressRepository.save(a2);
        restaurantRepository.save(r1);
        menuRepository.save(m1);
        categoryRepository.save(c1);
        itemRepository.save(i1);
        ingredientRepository.save(ing1);
        ingredientRepository.save(ing2);
        ingredientRepository.save(ing3);
        ingredientRepository.save(ing4);
        ingredientRepository.save(ing5);
        ingredientRepository.save(ing6);
        ingredientRepository.save(ing7);
        itemIngredientRepository.save(ii1);
        itemIngredientRepository.save(ii2);
        itemIngredientRepository.save(ii3);
        itemIngredientRepository.save(ii4);
        itemIngredientRepository.save(ii5);
        itemIngredientRepository.save(ii6);
        itemIngredientRepository.save(ii7);
        ingredientRepository.save(ing1);
        ingredientRepository.save(ing2);
        ingredientRepository.save(ing3);
        ingredientRepository.save(ing4);
        ingredientRepository.save(ing5);
        ingredientRepository.save(ing6);
        ingredientRepository.save(ing7);
        itemIngredientRepository.save(ii1);
        itemIngredientRepository.save(ii2);
        itemIngredientRepository.save(ii3);
        itemIngredientRepository.save(ii4);
        itemIngredientRepository.save(ii5);
        itemIngredientRepository.save(ii6);
        itemIngredientRepository.save(ii7);
        userRepository.save(u1);
        userRepository.save(u2);
        orderRepository.save(o1);
        orderItemRepository.save(oi1);
        addressRepository.save(a3);
        restaurantRepository.save(r2);
        menuRepository.save(m2);
        categoryRepository.save(c2);
        itemRepository.save(i2);
        itemIngredientRepository.save(ii8);
        itemIngredientRepository.save(ii9);
        itemIngredientRepository.save(ii10);
        itemIngredientRepository.save(ii11);
        itemIngredientRepository.save(ii12);
        itemIngredientRepository.save(ii13);
    }
}