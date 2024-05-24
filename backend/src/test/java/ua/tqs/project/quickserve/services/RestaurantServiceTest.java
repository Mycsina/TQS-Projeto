package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RestaurantServiceTest {
    protected static final Logger logger = LogManager.getLogger(RestaurantServiceTest.class);

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        Address restaurantAddress1 = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address restaurantAddress2 = new Address("Rua do Burger", "Porto", "4200-055", "Portugal");
        Address restaurantAddress3 = new Address("Rua do KFC", "Porto", "4200-055", "Portugal");
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();
        User manager1 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        User manager2 = new User("Burger King Manager", "1234", RoleEnum.MANAGER, "burgerking.mc.pt", 123123123);
        User manager3 = new User("KFC Manager", "1234", RoleEnum.MANAGER, "kfc.mc.pt", 123123123);


        Restaurant restaurant1 = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, menu1, manager1); restaurant1.setId(1L);
        Restaurant restaurant2 = new Restaurant("Burger King", "Number 2 in the fast food industry!", 123123123, State.OPEN, restaurantAddress2, menu2, manager2); restaurant2.setId(2L);
        Restaurant restaurant3 = new Restaurant("KFC", "Number 3 in the fast food industry!", 123123123, State.OPEN, restaurantAddress3, menu3, manager3); restaurant3.setId(3L);

        List<Restaurant> allRestaurants = Arrays.asList(restaurant1, restaurant2, restaurant3);

        Mockito.when(restaurantRepository.findById(restaurant1.getId())).thenReturn(Optional.of(restaurant1));
        Mockito.when(restaurantRepository.findById(restaurant2.getId())).thenReturn(Optional.of(restaurant2));
        Mockito.when(restaurantRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(restaurantRepository.findAll()).thenReturn(allRestaurants);

        Mockito.when(restaurantRepository.save(restaurant1)).thenReturn(restaurant1);
    }
    
    @Test
    void whenValidIdthenRestaurantShouldBeFound() {
        Restaurant found = restaurantService.getRestaurantById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenRestaurantShouldNotBeFound() {
        Restaurant found = restaurantService.getRestaurantById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveRestaurantthenRestaurantShouldBeReturned() {
        Address restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);

        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, menu, manager); restaurant.setId(1L);
        Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        assertThat(savedRestaurant).isNotNull();
    }

    @Test
    void whenDeleteRestaurantthenRestaurantShouldBeDeleted() {
        Long restaurantId = 1L;

        // Setup mock behavior
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(new Restaurant()));
            
        restaurantService.deleteRestaurantById(restaurantId);
        Mockito.verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }

}
