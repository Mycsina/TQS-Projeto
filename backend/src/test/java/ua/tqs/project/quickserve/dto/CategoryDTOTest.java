package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class CategoryDTOTest {

    @DisplayName("Test CategoryDTO Constructors")
    @Test
    void testConstructors() {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("Category");
        categoryDTO1.setItems(new ArrayList<ItemDTO>());

        assertThat(categoryDTO1.getName()).isEqualTo("Category");

        ItemDTO itemDTO1 = new ItemDTO();
        ItemDTO itemDTO2 = new ItemDTO();

        List<ItemDTO> items = Arrays.asList(itemDTO1, itemDTO2);
        
        CategoryDTO categoryDTO2 = new CategoryDTO("Category2", items);

        assertThat(categoryDTO2.getName()).isEqualTo("Category2");
        assertThat(categoryDTO2.getItems()).isEqualTo(items);
    }
}
