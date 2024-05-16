package ua.tqs.project.quickserve;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDateTime;

import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Status;

import ua.tqs.project.quickserve.repositories.AddressRepository;
import ua.tqs.project.quickserve.repositories.CategoryRepository;
import ua.tqs.project.quickserve.repositories.ItemIngredientRepository;
import ua.tqs.project.quickserve.repositories.ItemRepository;
import ua.tqs.project.quickserve.repositories.IngredientRepository;
import ua.tqs.project.quickserve.repositories.MenuRepository;
import ua.tqs.project.quickserve.repositories.OrderItemRepository;
import ua.tqs.project.quickserve.repositories.OrderRepository;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;
import ua.tqs.project.quickserve.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        // Restaurant
        Address a1 = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu m1 = new Menu();
        Restaurant r1 = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, a1, m1);
        r1.setTimes(LocalTime.of(10,0,0), LocalTime.of(4,0,0));
        
        // Category
        Category c1 = new Category("Burgers", m1);

        // Items
        Item i1 = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, r1, c1);
        
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

        // Users
        Address a2 = new Address("Casa do Joao", "Porto", "4200-055", "Portugal");
        User u1 = new User("Joao", "1234", RoleEnum.CLIENT, "joao@joao.pt", 123123123, a2);

        User u2 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);

        // Order
        Order o1 = new Order(LocalDateTime.now(), 5.0, r1, u1, PickupMethod.DELIVERY, Status.IN_MAKING);

        // OrderItem
        OrderItem oi1 = new OrderItem(5, i1, o1);

        // Save
        addressRepository.save(a1); addressRepository.save(a2);
        menuRepository.save(m1);
        restaurantRepository.save(r1);
        categoryRepository.save(c1);
        itemRepository.save(i1);
        ingredientRepository.save(ing1); ingredientRepository.save(ing2); ingredientRepository.save(ing3); ingredientRepository.save(ing4); ingredientRepository.save(ing5); ingredientRepository.save(ing6); ingredientRepository.save(ing7);
        itemIngredientRepository.save(ii1); itemIngredientRepository.save(ii2); itemIngredientRepository.save(ii3); itemIngredientRepository.save(ii4); itemIngredientRepository.save(ii5); itemIngredientRepository.save(ii6); itemIngredientRepository.save(ii7);
        userRepository.save(u1); userRepository.save(u2);
        orderRepository.save(o1);
        orderItemRepository.save(oi1);
    }
}