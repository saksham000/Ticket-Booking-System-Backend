package com.ticket.bookingsystem.movies.controllers.jpaController;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.UserData;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.UserDataRepo;

@RestController
public class UserController {

    @Autowired
    UserDataRepo userRepoService;

    @GetMapping(path = "users")
    public List<UserData> listAllUsers() {
        return userRepoService.findAll();
    }

    @GetMapping(path = "users/{id}")
    public UserData getUserById(@PathVariable int id) {
        if (userRepoService.findById(id).isPresent()) {
            return userRepoService.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
    }

    @PostMapping(path = "users")
    public UserData addNewUser(@RequestBody UserData user) {
        return userRepoService.save(user);
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable int id) {
        if (userRepoService.findById(id).isPresent()) {
            userRepoService.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }

    }

}
