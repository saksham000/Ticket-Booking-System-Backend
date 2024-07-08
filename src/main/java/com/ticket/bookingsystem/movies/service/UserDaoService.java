package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ticket.bookingsystem.movies.databasefiles.User;
import com.ticket.bookingsystem.movies.exceptions.UserNotFoundException;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int countUserId = 0;

    static {

        users.add(new User(countUserId++, "saksham"));
        users.add(new User(countUserId++, "yash"));
        users.add(new User(countUserId++, "tripathi"));
    }

    public List<User> listUsers() {
        return users;
    }

    public User findUserById(int id) {
        Optional<User> userOptional = users.stream().filter(user -> user.getUserId() == id).findFirst();
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User with id " + id + " not found");
        }else{
            return userOptional.get();
        }
    }

    public User addNewUser(User user) {
        user.setUserId(countUserId++);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        Predicate<? super User> predicate = user -> user.getUserId() == id;
        users.removeIf(predicate);
    }
}
