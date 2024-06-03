package ua.tqs.project.quickserve.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    String name;
    List<ItemDTO> items;

    public CategoryDTO(String name, List<ItemDTO> items) {
        this.name = name;
        this.items = items;
    }

    public CategoryDTO() {
    }
}
