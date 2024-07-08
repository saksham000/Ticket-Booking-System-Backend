package com.ticket.bookingsystem.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.bookingsystem.movies.databasefiles.User;
import com.ticket.bookingsystem.movies.exceptions.UserNotFoundException;
import com.ticket.bookingsystem.movies.service.UserDaoService;

@RestController
public class UserController {

    @Autowired
    UserDaoService userService;

    @GetMapping(path = "users")
    public List<User> listAllUsers() {
        return userService.listUsers();
    }

    @GetMapping(path = "users/{id}")
    public User getUserById(@PathVariable int id) {
        try {
            return userService.findUserById(id);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @PostMapping(path = "users")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable int id) {
        try {
            userService.findUserById(id);
            userService.deleteUser(id);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

}
