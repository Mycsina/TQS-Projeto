package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.repositories.MenuRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
    protected static final Logger logger = LogManager.getLogger(MenuServiceTest.class);

    @Mock( lenient = true)
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        Menu menu1 = new Menu(); menu1.setId(1L);
        Menu menu2 = new Menu(); menu2.setId(2L);
        Menu menu3 = new Menu(); menu3.setId(3L);

        List<Menu> allMenus = Arrays.asList(menu1, menu2, menu3);

        Mockito.when(menuRepository.findById(menu1.getId())).thenReturn(Optional.of(menu1));
        Mockito.when(menuRepository.findById(menu2.getId())).thenReturn(Optional.of(menu2));
        Mockito.when(menuRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(menuRepository.findAll()).thenReturn(allMenus);

        Mockito.when(menuRepository.save(menu1)).thenReturn(menu1);
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

}
