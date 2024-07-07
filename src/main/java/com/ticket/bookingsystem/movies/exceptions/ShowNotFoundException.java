package com.ticket.bookingsystem.movies.exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String message) {
        super(message);
    }
}