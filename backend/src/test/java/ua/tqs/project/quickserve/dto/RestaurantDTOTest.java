package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

class RestaurantDTOTest {

    @DisplayName("Test RestaurantDTO Constructors")
    @Test
    void testConstructors() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(1L);
        restaurantDTO.setName("Restaurant");
        restaurantDTO.setDescription("Description");
        restaurantDTO.setPhoneNumber(123456789);
        restaurantDTO.setOpeningTime(LocalTime.of(10, 0));
        restaurantDTO.setClosingTime(LocalTime.of(22, 0));
        restaurantDTO.setState("OPEN");
        restaurantDTO.setAddress(new Address());
        restaurantDTO.setManagerId(1L);

        assertThat(restaurantDTO.getName()).isEqualTo("Restaurant");
        assertThat(restaurantDTO.getRestaurantId()).isEqualTo(1L);
        assertThat(restaurantDTO.getDescription()).isEqualTo("Description");
        assertThat(restaurantDTO.getPhoneNumber()).isEqualTo(123456789);
        assertThat(restaurantDTO.getOpeningTime()).isEqualTo(LocalTime.of(10, 0));
        assertThat(restaurantDTO.getClosingTime()).isEqualTo(LocalTime.of(22, 0));
        assertThat(restaurantDTO.getState()).isEqualTo("OPEN");
        assertThat(restaurantDTO.getAddress()).isNotNull();
        assertThat(restaurantDTO.getManagerId()).isEqualTo(1L);

        Address address = new Address();

        Menu menu = new Menu();
        menu.setId(1L);

        User manager = new User();
        manager.setId(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setName("Restaurant2");
        restaurant.setDescription("Description2");
        restaurant.setPhone(987654321);
        restaurant.setOpeningTime(LocalTime.of(9, 0));
        restaurant.setClosingTime(LocalTime.of(23, 0));
        restaurant.setState(State.CLOSED);
        restaurant.setAddress(address);
        restaurant.setManager(manager);

        RestaurantDTO restaurantDTO2 = new RestaurantDTO(restaurant);

        assertThat(restaurantDTO2.getName()).isEqualTo("Restaurant2");
        assertThat(restaurantDTO2.getRestaurantId()).isEqualTo(2L);
    }

}
