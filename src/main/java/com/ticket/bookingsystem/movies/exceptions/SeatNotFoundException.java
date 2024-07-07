package com.ticket.bookingsystem.movies.exceptions;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String message) {
        super(message);
    }
}