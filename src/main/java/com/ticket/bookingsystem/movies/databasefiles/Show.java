package com.ticket.bookingsystem.movies.databasefiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`show`")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showId;
    private int movieIdShow;
    private LocalTime showStart;
    private int numberOfSeats;

    @JsonIgnore
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    public Show(int movieIdShow, LocalTime showStart, int numberOfSeats) {
        this.movieIdShow = movieIdShow;
        this.showStart = showStart;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            Seat seat = new Seat(i);
            seat.setShow(this);
            seats.add(seat);
        }
    }

    public Show() {
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    // Constructor

    // Getters
    public int getShowId() {
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

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setMovieIdShow(int movieIdShow) {
        this.movieIdShow = movieIdShow;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
        for (Seat seat : seats) {
            seat.setShow(this);
        }
    }

    public Seat getSeat(int seatNumber) {
        return seats.stream().filter(seats -> seats.getSeatNo() == seatNumber).findFirst()
                .orElse(null);
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
