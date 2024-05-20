package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.repositories.UserRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(UserServiceTest.class);

    @Mock( lenient = true)
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        Address address1 = new Address("Rua do Amial", "Porto", "4200-055", "Portugal"); address1.setId(1L);
        Address address2 = new Address("Rua do Abc", "Viseu", "3323-231", "Portugal"); address2.setId(2L);

        User user1 = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address1); user1.setId(1L);
        User user2 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123); user2.setId(2L);
        User user3 = new User("Guy", "1234", RoleEnum.CLIENT, "guy.guy.pt", 123123123, address2); user3.setId(3L);

        List<User> allUsers = Arrays.asList(user1, user2, user3);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
        Mockito.when(userRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);

        Mockito.when(userRepository.save(user1)).thenReturn(user1);
    }
    
    @Test
    void whenValidIdthenUserShouldBeFound() {
        User found = userService.getUserById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenUserShouldNotBeFound() {
        User found = userService.getUserById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveUserthenUserShouldBeReturned() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal"); address.setId(1L);

        User user = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address); user.setId(1L);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        
        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
    }

    @Test
    void whenDeleteUserthenUserShouldBeDeleted() {
        Long userId = 1L;

        // Setup mock behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
            
        userService.deleteUserById(userId);
        Mockito.verify(userRepository, times(1)).deleteById(userId);
    }

}
