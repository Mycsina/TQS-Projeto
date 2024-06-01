package ua.tqs.project.quickserve.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    
    long restaurantId;
    List<CategoryDTO> categories;

    public MenuDTO(long restaurantId, List<CategoryDTO> categories) {
        this.restaurantId = restaurantId;
        this.categories = categories;
    }

    public MenuDTO() {
    }
}
