package com.ocean.services;

import com.ocean.models.User;
import com.ocean.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    UserDao userDao;
    User shane;

    @BeforeEach
    void setUp() { this.userService = new UserService(userDao);
        shane = mock(User.class);

    }

    @Test
    void forGotInfo() {
        when(userService.checkForUser("Shane")).thenReturn(shane);

        assertNull(userService.forGotInfo("NotARealUsername"));
        assertNotNull(userService.forGotInfo("Shane"));
    }

    @Test
    void checkForUserReturnNull() {
        assertNull(userService.checkForUser("ANameThatIsntInTheDatabase"));
    }

    @Test
    void getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        assertNotNull(allUsers);
        System.out.println("List of Users: " + allUsers);
    }
}