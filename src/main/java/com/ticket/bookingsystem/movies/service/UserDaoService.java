package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ticket.bookingsystem.movies.databasefiles.User;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int countUserId = 0;

    static {

        users.add(new User("saksham", countUserId++));
        users.add(new User("yash", countUserId++));
        users.add(new User("tripathi", countUserId++));
    }

    public List<User> listUsers() {
        return users;
    }

    public User findUserById(int id){
        return users.stream().filter(user -> user.getUserId() == id).findFirst().get();
    }

    public User addNewUser(User user){
        user.setUserId(countUserId++);
        users.add(user);
        return user;
    }

    public void deleteUser(int id){
        Predicate<? super User> predicate = user -> user.getUserId().equals(id);
        users.removeIf(predicate);
    }
}
