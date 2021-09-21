package com.ocean.controller;

import com.ocean.models.*;
import com.ocean.services.EmailService;
import com.ocean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController("userController")
@RequestMapping(value= "api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class UserController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService){ this.userService = userService; this.emailService = emailService;}


    //Creates a Session for the user logged in
    @GetMapping("check-session")
    public Response checkSession(HttpSession session){
        Response response;
        User user = (User) session.getAttribute("loggedInUser");
        if(user != null){
            user.setPassword(null);
            response = new Response(true,"session found", user);
        }else{
            response = new Response(false, "session not found", null);
        }
        return response;
    }

    //Checks to see if user is in database other wise it'll reject their log in
    @PostMapping("login")
    public Response login (HttpSession session, @RequestBody User user) {
        Response response;
        User tempUser = this.userService.login(user);
        if (tempUser != null) {
            session.setAttribute("loggedInUser", user);
            tempUser.setPassword(null);
            response = new Response(true, "logged In and session created", tempUser);
        } else {
            response = new Response(false, "Invalid username or password. (Remember, these are case sensitive!)",null);
        }
        return response;
    }

    //Logs the user out and ends the session
    @GetMapping("logout")
    public Response logout(HttpSession session){
        session.setAttribute("loggedInUser", null);
        return new Response(true,"You have logged out and session terminated", null);
    }

    //Creates a new user
    @PostMapping("user")
    public Response createUser(@RequestBody User user) {
        Response response;
        User tempUser = this.userService.createUser(user);
        if (tempUser != null) {
            this.emailService.welcomeEmail(tempUser.getEmail(), tempUser.getFirstName());
            user.setPassword(null);
            response = new Response(true, "user has been created", user);
        } else {
            response = new Response(false, "This User already exists", null);
        }
        return response;
    }

    //Get all users
    @GetMapping("user")
    public Response getAllUsers(){
        Response response;
        List<User> allUsers = this.userService.getAllUsers();
        if(allUsers != null){
            response = new Response(true, "here are all the users", allUsers);
        } else {
            response = new Response(false, "Failed to find", null);
        }
        return response;
    }

    //Checks username in database and will send them email with password
    @GetMapping("forgot/{username}")
    public Response forGotInfo(@PathVariable String username){
        Response response;
        User tempUser = this.userService.forGotInfo(username);
        if(tempUser != null){
            String pass = this.emailService.sendNewPassword(tempUser.getEmail(), tempUser.getFirstName());
            tempUser.setPassword(pass);
            this.userService.updateUser(tempUser);
            response = new Response(true, "An email has been sent to this account holder", tempUser.getEmail());
        }else{
            response = new Response(false, "There is no user by the username:" + username, null);
        }
        return response;
    }

    //Will update the profile of this user
    @PutMapping("updateUser")
    public Response updateUser(@RequestBody User user){
        Response response;
        User updateUser = this.userService.updateUser(user);
        if(updateUser == user){
            user.setPassword(null);
            return new Response(true,"Profile has been updated",user);
        }else{
            return new Response(false,"Profile has not been updated", null);
        }
    }

    //Will get user with matching Id
    @GetMapping("user/{id}")
    public Response getUserById(@PathVariable Integer id){
        Response response;
        User user = (User) this.userService.getUserById(id);
        if(user != null){
            user.setPassword(null);
            response = new Response(true, "Here is the user", user);
        }else{
            response = new Response(false, "User was not found",null);
        }
        return response;
    }
}
