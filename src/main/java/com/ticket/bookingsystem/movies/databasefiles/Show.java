package com.ticket.bookingsystem.movies.databasefiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
// jakarta. validation valid

public class Show {
    private int id;
    private int movieIdShow;
    private LocalTime showStart;

    @JsonIgnore
    private List<Seat> seats;

    // Constructor
    public Show(int id, int movieIdShow, LocalTime showStart, int numberOfSeats) {
        this.id = id;
        this.movieIdShow = movieIdShow;
        this.showStart = showStart;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    // Getters
    public int getShowId() {
        return id;
    }

    public int getMovieIdShow() {
        return movieIdShow;
    }

    public LocalTime getShowStart() {
        return showStart;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    // Setters
    public void setShowId(int id) {
        this.id = id;
    }

    public void setMovieIdShow(int movieIdShow) {
        this.movieIdShow = movieIdShow;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }

    public Seat getSeat(int seatNumber) {
        return seats.stream().filter(seats -> seats.getSeatNo() == seatNumber).findFirst()
                .orElse(null);
    }

    // toString method
    @Override
    public String toString() {
        return "Show[" +
                "showId=" + id +
                ", showStart=" + showStart +
                ", movieIdShow=" + movieIdShow +
                ']';
    }
}
