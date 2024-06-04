package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FullOrderDTOTest {

    @DisplayName("Test FullOrderDTO Constructors")
    @Test
    void testConstructors() {
        
        FullOrderDTO fullOrderDTO = new FullOrderDTO();
        fullOrderDTO.setOrder(new BaseOrderDTO());
        fullOrderDTO.setItems(new long[]{1L});

        ItemIngredientDTO itemIngredientDTO = new ItemIngredientDTO();
        fullOrderDTO.setItemIngredients(new ItemIngredientDTO[]{itemIngredientDTO});

        assertThat(fullOrderDTO.getOrder()).isNotNull();
        assertThat(fullOrderDTO.getItems()).containsExactly(1L);
        assertThat(fullOrderDTO.getItemIngredients()).containsExactly(itemIngredientDTO);
    }

}
