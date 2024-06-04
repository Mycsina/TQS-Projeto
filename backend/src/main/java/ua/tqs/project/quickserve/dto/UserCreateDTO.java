package ua.tqs.project.quickserve.dto;

import lombok.Data;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.User;

@Data
public class UserCreateDTO {
    private String name;
    private String password;
    private String email;
    private String phone;

    public User toUser() {
        return new User(name, password, RoleEnum.CLIENT, email, Integer.parseInt(phone));
    }
}

