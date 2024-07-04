package com.ticket.bookingsystem.movies.databasefiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private int showId;
    private int movieIdShow;
    private LocalTime showStart;
    private List<Seat> seats;

    // Constructor
    public Show(int showId, int movieIdShow, LocalTime showStart) {
        this.showId = showId;
        this.movieIdShow = movieIdShow;
        this.showStart = showStart;
        this.seats = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            seats.add(new Seat(i));
        }
    }

    // Getters
    public Integer getShowId() {
        return showId;
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
    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setMovieIdShow(int movieIdShow) {
        this.movieIdShow = movieIdShow;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }

    public Seat getSeat(int seatNumber) {
        return seats.stream().filter(seats -> seats.getSeatNo() == seatNumber).findFirst().orElse(null);
    }

    // toString method
    @Override
    public String toString() {
        return "Show[" +
                "showId=" + showId +
                ", showStart=" + showStart +
                ", movieIdShow=" + movieIdShow +
                ']';
    }
}
