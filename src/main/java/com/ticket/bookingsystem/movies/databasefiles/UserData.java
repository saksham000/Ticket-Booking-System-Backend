package com.ticket.bookingsystem.movies.databasefiles;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserData {

    public UserData() {

    }

    @GeneratedValue
    @Id
    private int userId;
    private String username;



     @OneToMany(mappedBy = "userId")
    private List<Seat> seats;



    public UserData(int userId, String username) {
        super();
        this.userId = userId;
        this.username = username;

    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // toString method
    @Override
    public String toString() {
        return "User[" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ']';
    }
}
