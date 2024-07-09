package com.ticket.bookingsystem.movies.databasefiles;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private LocalDate date;
    private String city;
    private String theaterName;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    public Movie(){
        
    }

    // Constructor
    public Movie(int id, String movieName, LocalDate date, String city, String theaterName) {
        super();
        this.id = id;
        this.movieName = movieName;
        this.date = date;
        this.city = city;
        this.theaterName = theaterName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getTheatreName() {
        return theaterName;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTheatreName(String theaterName) {
        this.theaterName = theaterName;
    }

    // toString method
    @Override
    public String toString() {
        return "Movie[" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", theatreName='" + theaterName + '\'' +
                ']';
    }
}
