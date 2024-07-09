package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ticket.bookingsystem.movies.databasefiles.UserData;
import com.ticket.bookingsystem.movies.exceptions.UserNotFoundException;

@Service
public class UserDaoService {

    private static List<UserData> users = new ArrayList<>();

    private static int countUserId = 0;

    static {

        users.add(new UserData(countUserId++, "saksham"));
        users.add(new UserData(countUserId++, "yash"));
        users.add(new UserData(countUserId++, "tripathi"));
    }

    public List<UserData> listUsers() {
        return users;
    }

    public UserData findUserById(int id) {
        Optional<UserData> userOptional = users.stream().filter(user -> user.getUserId() == id).findFirst();
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User with id " + id + " not found");
        }else{
            return userOptional.get();
        }
    }

    public UserData addNewUser(UserData user) {
        user.setUserId(countUserId++);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        Predicate<? super UserData> predicate = user -> user.getUserId() == id;
        users.removeIf(predicate);
    }
}
