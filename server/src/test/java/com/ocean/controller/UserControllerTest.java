package com.ocean.controller;

import com.ocean.models.Response;
import com.ocean.models.User;
import com.ocean.services.EmailService;
import com.ocean.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    UserController userController;

    @Mock
    UserService userService;
    @Mock
    EmailService emailService;
    @Mock
    HttpSession session;

    @BeforeEach
    void setUp() {
        this.userController = new UserController(userService, emailService);
    }

    @Test
    void loginReturnNull() {
        //assign
        User user = new User("Shane", "Password");
        Response expectedResult = new Response(false, "Invalid username or password. (Remember, these are case sensitive!)",null);

        //Mock
        Mockito.when(userService.login(user)).thenReturn(null);
        //act

        Response actualResult = this.userController.login(session, user);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void loginReturnNotNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        Response expectedResult = new Response(true, "logged In and session created", tempUser);

        //Mock
        Mockito.when(userService.login(tempUser)).thenReturn(tempUser);
        //act

        Response actualResult = this.userController.login(session, tempUser);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void logout() {
        //assign
        Response expectedResult = new Response(true,"You have logged out and session terminated", null);
        session.setAttribute("loggedInUser", null);

        //act
        Response actualResult = this.userController.logout(session);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createUserReturnNull() {
        //assign
        User tempUser = new User("shane","pass1234");
        Response expectedResult = new Response(false, "This User already exists", null);

        //Mock
        Mockito.when(userService.createUser(tempUser)).thenReturn(null);
        //act
        Response actualResult = this.userController.createUser(tempUser);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void createUserReturnNotNull() {
        //assign
        User tempUser = new User("shane","pass1234");
        Response expectedResult = new Response(true, "user has been created", tempUser);

        //Mock
        Mockito.when(userService.createUser(tempUser)).thenReturn(tempUser);
        //act
        Response actualResult = this.userController.createUser(tempUser);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getAllUsersReturnNull() {
        //assign
        Response expectedResult = new Response(false, "Failed to find", null);

        //Mock
        Mockito.when(userService.getAllUsers()).thenReturn(null);
        //act
        Response actualResult = this.userController.getAllUsers();
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void getAllUsersReturnNotNull() {
        //assign
        List<User> user = new ArrayList<>();
        user.add(new User("shane","pass1234"));
        Response expectedResult = new Response(true, "here are all the users", user);

        //Mock
        Mockito.when(userService.getAllUsers()).thenReturn(user);
        //act
        Response actualResult = this.userController.getAllUsers();
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }


    @Test
    void forGotInfoReturnNull() {
        //assign
        String username = "shane";
        Response expectedResult = new Response(false, "There is no user by the username:" + username, null);

        //Mock
        Mockito.when(userService.forGotInfo(username)).thenReturn(null);
        //act
        Response actualResult = this.userController.forGotInfo(username);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void forGotInfoReturnNotNull() {
        //assign
        User tempUser = new User("shane","pass1234");
        String name = "shane";
        String email = "testing@email.com";
        Response expectedResult = new Response(true, "An email has been sent to this account holder", tempUser.getEmail());
        //Mock
        Mockito.when(userService.forGotInfo(name)).thenReturn(tempUser);
        //act
        Response actualResult = this.userController.forGotInfo(name);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    @Test
    void updateUserReturnNull() {
        //assign
        User tempUser = new User("shane","pass1234");
        Response expectedResult = new Response(false,"Profile has not been updated", null);

        //Mock
        Mockito.when(userService.updateUser(tempUser)).thenReturn(null);
        //act
        Response actualResult = this.userController.updateUser(tempUser);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void updateUserReturnNotNull() {
        //assign
        User user = new User("shane","pass1234");
        Response expectedResult = new Response(true,"Profile has been updated",user);

        //Mock
        Mockito.when(userService.updateUser(user)).thenReturn(user);
        //act
        Response actualResult = this.userController.updateUser(user);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getUserByIdReturnNull() {
        //assign
        Integer id = 1;
        User user = new User("shane","pass1234");
        Response expectedResult = new Response(false, "User was not found",null);

        //Mock
        Mockito.when(userService.getUserById(id)).thenReturn(null);
        //act
        Response actualResult = this.userController.getUserById(id);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void getUserByIdReturnNotNull() {
        //assign
        Integer id = 1;
        User user = new User("shane","pass1234");
        Response expectedResult = new Response(true, "Here is the user", user);

        //Mock
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        //act
        Response actualResult = this.userController.getUserById(id);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}