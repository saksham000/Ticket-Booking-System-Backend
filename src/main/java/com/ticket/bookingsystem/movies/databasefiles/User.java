package com.ticket.bookingsystem.movies.databasefiles;

public class User {
    private int userId;
    private String username;

    // Constructor
    public User(int userId, String username) {
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
