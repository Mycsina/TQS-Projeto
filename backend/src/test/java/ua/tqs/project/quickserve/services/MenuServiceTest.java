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

import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.dto.MenuDTO;
import ua.tqs.project.quickserve.dto.CategoryDTO;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.repositories.MenuRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MenuServiceTest {
    protected static final Logger logger = LogManager.getLogger(MenuServiceTest.class);

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        Restaurant restaurant1 = new Restaurant(); restaurant1.setId(1L);
        Restaurant restaurant2 = new Restaurant(); restaurant2.setId(2L);
        Restaurant restaurant3 = new Restaurant(); restaurant3.setId(3L);

        Menu menu1 = new Menu(restaurant1); menu1.setId(1L);
        Menu menu2 = new Menu(restaurant2); menu2.setId(2L);
        Menu menu3 = new Menu(restaurant3); menu3.setId(3L);

        List<Menu> allMenus = Arrays.asList(menu1, menu2, menu3);

        Mockito.when(menuRepository.findById(menu1.getId())).thenReturn(Optional.of(menu1));
        Mockito.when(menuRepository.findById(menu2.getId())).thenReturn(Optional.of(menu2));
        Mockito.when(menuRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(menuRepository.findAll()).thenReturn(allMenus);

        Mockito.when(menuRepository.save(menu1)).thenReturn(menu1);
        Mockito.when(menuRepository.save(any())).thenReturn(menu1);
    }
    
    @Test
    void whenValidIdthenMenuShouldBeFound() {
        Menu found = menuService.getMenuById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenMenuShouldNotBeFound() {
        Menu found = menuService.getMenuById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveMenuthenMenuShouldBeReturned() {
        Menu menu = new Menu(); menu.setId(1L);
        Mockito.when(menuRepository.save(menu)).thenReturn(menu);
        
        Menu savedMenu = menuService.save(menu);
        assertThat(savedMenu).isNotNull();
    }

    @Test
    void whenDeleteMenuthenMenuShouldBeDeleted() {
        Long menuId = 1L;

        // Setup mock behavior
        when(menuRepository.findById(menuId)).thenReturn(Optional.of(new Menu()));
            
        menuService.deleteMenuById(menuId);
        Mockito.verify(menuRepository, times(1)).deleteById(menuId);
    }

    @Test
    void whenDefineMenuThenMenuShouldBeDefined() {
        Menu menu = menuRepository.findById(1L).get();

        MenuDTO menuDTO = new MenuDTO();
        CategoryDTO categoryDTO = new CategoryDTO();
        menuDTO.setCategories(Arrays.asList(categoryDTO));

        menuService.defineMenu(menuDTO, menu.getRestaurant());
        Mockito.verify(categoryService, times(1)).defineCategory(any(CategoryDTO.class), any(Menu.class));
    }

    @Test
    void whenConvertMenuToDTOThenMenuDTOShouldBeReturned() {
        Menu menu = menuRepository.findById(1L).get();

        assertThat(menuService.convertMenuToDTO(menu)).isNotNull();
    }
}
