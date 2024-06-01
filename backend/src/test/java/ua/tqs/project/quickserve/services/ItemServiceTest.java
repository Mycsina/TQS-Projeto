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
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.dto.ItemDTO;
import ua.tqs.project.quickserve.repositories.ItemRepository;

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
class ItemServiceTest {
    protected static final Logger logger = LogManager.getLogger(ItemServiceTest.class);

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemIngredientService itemIngredientService;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        Category category = new Category("Burgers", menu); category.setId(1L);
        restaurant.setTimes("10:00:00", "04:00:00");


        Item item1 = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category); item1.setId(1L);
        Item item2 = new Item("CBO", "Bacon delight!", "./images/cbopic", 7.0, restaurant, category); item2.setId(2L);
        Item item3 = new Item("McChicken", "Chicken!", "./images/mcchickenpic", 4.5, restaurant, category); item3.setId(3L);

        List<Item> allItems = Arrays.asList(item1, item2, item3);

        Mockito.when(itemRepository.findById(item1.getId())).thenReturn(Optional.of(item1));
        Mockito.when(itemRepository.findById(item2.getId())).thenReturn(Optional.of(item2));
        Mockito.when(itemRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(itemRepository.findAll()).thenReturn(allItems);

        Mockito.when(itemRepository.save(item1)).thenReturn(item1);
        Mockito.when(itemRepository.findByCategoryId(category.getId())).thenReturn(Arrays.asList(item1, item2, item3));

        Mockito.when(itemIngredientService.getItemIngredients(item1.getId())).thenReturn(Arrays.asList());
    }
    
    @Test
    void whenValidIdthenItemShouldBeFound() {
        Item found = itemService.getItemById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenItemShouldNotBeFound() {
        Item found = itemService.getItemById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenGetItemsByCategorythenReturnItems() {        
        List<Item> found = itemService.getItemsByCategory(1L);
        assertThat(found).hasSize(3);
    }

    @Test
    void whenSaveItemthenItemShouldBeReturned() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        Category category = new Category("Burgers", menu);
        restaurant.setTimes("10:00:00", "04:00:00");

        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category); item.setId(1L);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        
        Item savedItem = itemService.save(item);
        assertThat(savedItem).isNotNull();
    }

    @Test
    void whenDeleteItemthenItemShouldBeDeleted() {
        Long itemId = 1L;

        // Setup mock behavior
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(new Item()));
            
        itemService.deleteItemById(itemId);
        Mockito.verify(itemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void whenConvertItemListToDTOsthenReturnDTOs() {
        List<Item> allItems = itemRepository.findAll();
        List<ItemDTO> allItemDTOs = itemService.convertItemListToDTOs(allItems);
        assertThat(allItemDTOs).hasSize(3);
    }

    @Test
    void whenConvertItemToDTOthenReturnDTO() {
        Item item = itemRepository.findById(1L).get();
        ItemDTO itemDTO = itemService.convertItemToDTO(item);
        assertThat(itemDTO).isNotNull();
    }
}
