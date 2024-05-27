package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.PickupMethod;

class BaseOrderDTOTest {

    @DisplayName("Test BaseOrderDTO Constructors")
    @Test
    void testConstructors() {
        BaseOrderDTO baseOrderDTO = new BaseOrderDTO();
        baseOrderDTO.setOrderId(1L);

        assertThat(baseOrderDTO.getOrderId()).isEqualTo(1L);

        Address address = new Address();
        BaseOrderDTO baseOrderDTO2 = new BaseOrderDTO(1L, 10.0, LocalDateTime.now(), address, 1L, 1L, PickupMethod.DELIVERY);

        assertThat(baseOrderDTO2.getPrice()).isEqualTo(10.0);
        assertThat(baseOrderDTO2.getScheduledTime()).isNotNull();
        assertThat(baseOrderDTO2.getDeliveryAddress()).isEqualTo(address);
        assertThat(baseOrderDTO2.getRestaurantId()).isEqualTo(1L);
        assertThat(baseOrderDTO2.getUserId()).isEqualTo(1L);


        Order order = new Order();
        order.setId(2L);
        order.setScheduledTime(LocalDateTime.now());

        Address deliveryAddress = new Address();
        order.setDeliveryAddress(deliveryAddress);
        
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        order.setRestaurant(restaurant);

        User user = new User();
        user.setId(1L);
        order.setUser(user);

        order.setPickupMethod(PickupMethod.DELIVERY);

        BaseOrderDTO baseOrderDTO3 = new BaseOrderDTO(order);

        assertThat(baseOrderDTO3.getOrderId()).isEqualTo(2L);
        assertThat(baseOrderDTO3.getRestaurantId()).isEqualTo(1L);
        assertThat(baseOrderDTO3.getUserId()).isEqualTo(1L);
    }

}
