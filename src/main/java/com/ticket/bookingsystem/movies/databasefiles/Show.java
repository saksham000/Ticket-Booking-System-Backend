package com.ticket.bookingsystem.movies.databasefiles;

import java.time.LocalTime;

public class Show {
    private int showId;
    private int movieIdShow;
    private LocalTime showStart;

    // Constructor
    public Show(int showId,int movieIdShow, LocalTime showStart) {
        this.showId = showId;
        this.movieIdShow = movieIdShow;
        this.showStart = showStart;
    }

    // Getters
    public int getShowId() {
        return showId;
    }

    public int getMovieIdShow(){
        return movieIdShow;
    }

    public LocalTime getShowStart() {
        return showStart;
    }

    // Setters
    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setMovieIdShow(int movieIdShow){
        this.movieIdShow = movieIdShow;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
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
