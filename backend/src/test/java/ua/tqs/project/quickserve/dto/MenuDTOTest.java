package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class MenuDTOTest {

    @DisplayName("Test MenuDTO Constructors")
    @Test
    void testConstructors() {
        MenuDTO menuDTO1 = new MenuDTO();
        menuDTO1.setRestaurantId(1L);
        menuDTO1.setCategories(new ArrayList<CategoryDTO>());

        assertThat(menuDTO1.getRestaurantId()).isEqualTo(1L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        CategoryDTO categoryDTO2 = new CategoryDTO();

        List<CategoryDTO> categories = Arrays.asList(categoryDTO1, categoryDTO2);
        
        MenuDTO menuDTO2 = new MenuDTO(2L, categories);

        assertThat(menuDTO2.getRestaurantId()).isEqualTo(2L);
        assertThat(menuDTO2.getCategories()).isEqualTo(categories);
    }
}
