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

import ua.tqs.project.quickserve.dto.MenuDTO;
import ua.tqs.project.quickserve.dto.AddressDTO;
import ua.tqs.project.quickserve.dto.RestaurantDTO;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Restaurant;
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

    @Mock
    private MenuService menuService;

    @Mock
    private UserService userService;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        Address restaurantAddress1 = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address restaurantAddress2 = new Address("Rua do Burger", "Porto", "4200-055", "Portugal");
        Address restaurantAddress3 = new Address("Rua do KFC", "Porto", "4200-055", "Portugal");
        User manager1 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        User manager2 = new User("Burger King Manager", "1234", RoleEnum.MANAGER, "burgerking.mc.pt", 123123123);
        User manager3 = new User("KFC Manager", "1234", RoleEnum.MANAGER, "kfc.mc.pt", 123123123);

        Restaurant restaurant1 = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, manager1); restaurant1.setId(1L);
        Restaurant restaurant2 = new Restaurant("Burger King", "Number 2 in the fast food industry!", 123123123, State.OPEN, restaurantAddress2, manager2); restaurant2.setId(2L);
        Restaurant restaurant3 = new Restaurant("KFC", "Number 3 in the fast food industry!", 123123123, State.OPEN, restaurantAddress3, manager3); restaurant3.setId(3L);

        List<Restaurant> allRestaurants = Arrays.asList(restaurant1, restaurant2, restaurant3);

        Mockito.when(restaurantRepository.findById(restaurant1.getId())).thenReturn(Optional.of(restaurant1));
        Mockito.when(restaurantRepository.findById(restaurant2.getId())).thenReturn(Optional.of(restaurant2));
        Mockito.when(restaurantRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(restaurantRepository.findAll()).thenReturn(allRestaurants);
        Mockito.when(restaurantRepository.findOpenRestaurants()).thenReturn(allRestaurants);

        Mockito.when(restaurantRepository.save(restaurant1)).thenReturn(restaurant1);

        Mockito.when(addressService.getAddressByPostalCode(restaurantAddress1.getPostalCode())).thenReturn(restaurantAddress1);
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
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);

        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, manager); restaurant.setId(1L);
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

    @Test
    void whenConvertRestaurantToDTOthenRestaurantDTOShouldBeReturned() {
        Restaurant restaurant = restaurantRepository.findById(1L).get();

        assertThat(restaurantService.convertRestaurantToDTO(restaurant)).isNotNull();
    }

    @Test
    void whenConvertRestaurantListToDTOthenRestaurantDTOListShouldBeReturned() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        assertThat(restaurantService.convertRestaurantListToDTOs(allRestaurants)).isNotNull();
    }

    @Test
    void whenConvertDTOToRestaurantthenRestaurantShouldBeReturned() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName("McDonald's");
        restaurantDTO.setDescription("Number 1 in the fast food industry!");
        restaurantDTO.setPhoneNumber(123123123);
        restaurantDTO.setState(State.OPEN);
        restaurantDTO.setManagerId(1L);
        restaurantDTO.setAddress(new AddressDTO("Rua do Amial", "Porto", "4200-055", "Portugal"));

        assertThat(restaurantService.convertDTOToRestaurant(restaurantDTO)).isNotNull();
    }

    @Test
    void whenGetAllRestaurantsthenAllRestaurantsShouldBeReturned() {
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();

        assertThat(allRestaurants).isNotNull().hasSize(3);
    }

    @Test
    void whenGetOpenRestaurantsthenOpenRestaurantsShouldBeReturned() {
        List<Restaurant> openRestaurants = restaurantService.getOpenRestaurants();

        assertThat(openRestaurants).isNotNull().hasSize(3);
    }

    @Test
    void whenSetMenuthenMenuShouldBeImported() {
        MenuDTO menu = new MenuDTO();
        menu.setRestaurantId(1L);

        restaurantService.setMenu(menu);
        Mockito.verify(menuService, times(1)).defineMenu(menu, restaurantRepository.findById(1L).get());
    }
}
