package com.ocean.services;

import com.ocean.models.User;
import com.ocean.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){this.userDao = userDao;}

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Login
    public User login(User user){
        User tempUser = checkForUser(user.getUsername());
        //checks if the search returns a user object
        if(tempUser != null){
            //Checks to make sure their passwords match
            boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), tempUser.getPassword());
            if(isPasswordMatch){
                return tempUser;
            }
        }
        return null;
    }

    //Checks for if a User is in the Database
    public User checkForUser(String username){ return this.userDao.findUserByUsername(username); }

    //Register/signUp user
    public User createUser(User user) {
        //check to see if user is already in database
        User tempUser = checkForUser(user.getUsername());
        //If username is not found in database it will create the user
        if (tempUser == null) {
            String bcPass = user.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(bcPass);
            user.setPassword(encode);
            return this.userDao.save(user);
        }
        //if tempUser returns something from database it'll return null as username is in use
        return null;
    }

    //If User forgot login info
    public User forGotInfo(String username) {
        //check to see if user is already in database
        return checkForUser(username);
    }

    //Will update User
    public User updateUser(User user) {
        //Gets the user from Database
        User dataBaseUser =this.userDao.findUserByUsername(user.getUsername());
        //Checks to see if a result was returned
        if(dataBaseUser != null){
            //To make sure the ID & Password doesn't get changed by anyone
            user.setUserId(dataBaseUser.getUserId());
            user.setPassword(dataBaseUser.getPassword());
            //Executes the update
            this.userDao.save(user);
            //Returns the updated user
            return user;
        }
        return null;
    }

    //Will get a user by Id
    public User getUserById(Integer userId) {
        return this.userDao.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = this.userDao.findAll();
        for(User a : users){
            a.setPassword(null);
        }
        return users;
    }
}


