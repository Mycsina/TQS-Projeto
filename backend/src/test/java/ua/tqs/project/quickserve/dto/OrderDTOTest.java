package ua.tqs.project.quickserve.dto;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.PickupMethod;

class OrderDTOTest {

    @DisplayName("Test OrderDTO Constructors")
    @Test
    void testConstructors() {
        OrderDTO orderDTO = new OrderDTO();
        BaseOrderDTO baseOrderDTO = new BaseOrderDTO();
        baseOrderDTO.setPrice(10.0);
        orderDTO.setOrder(baseOrderDTO);

        assertThat(orderDTO.getOrder().getPrice()).isEqualTo(10.0);

        Order order = new Order();
        order.setId(2L);
        order.setTotalPrice(10.0);
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

        BaseItemDTO baseItemDTO = new BaseItemDTO();
        baseItemDTO.setName("Item");

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem(baseItemDTO);

        List<ItemDTO> items = List.of(itemDTO);

        OrderDTO orderDTO2 = new OrderDTO(order, items);

        assertThat(orderDTO2.getOrder().getPrice()).isEqualTo(10.0);
        assertThat(orderDTO2.getItems()).containsExactly(itemDTO);
    }
}
