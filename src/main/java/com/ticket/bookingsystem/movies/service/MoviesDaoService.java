package com.ticket.bookingsystem.movies.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.ticket.bookingsystem.movies.databasefiles.Movie;

@Service
public class MoviesDaoService {

    private static List<Movie> movies = new ArrayList<>();
    private static int movieId = 1;

    static {

        movies.add(new Movie(movieId++, "Mugen Train", LocalDate.now().plusDays(5), "Ludhiana", "Pavillion PVR"));
        movies.add(new Movie(movieId++, "Mugen Train", LocalDate.now().plusDays(5), "Ludhiana", "shivalik PVR"));
        movies.add(new Movie(movieId++, "Mugen Train", LocalDate.now().plusDays(5), "Chandigarh", "elante PVR"));
        movies.add(new Movie(movieId++, "Hashira ARC", LocalDate.now().plusDays(15), "Chandigarh", "solitAir"));
        movies.add(new Movie(movieId++, "Swordssmith ARC", LocalDate.now().plusDays(25), "Amritsar", "DBS"));
    }

    public List<Movie> findMovieByName(String movieName, String city ){
        Predicate<Movie> predicate = movie -> movie.getMovieName().equalsIgnoreCase(movieName);
        Predicate<Movie> predicate2 = movie -> movie.getCity().equalsIgnoreCase(city);
        Predicate<Movie> combined = predicate.and(predicate2);    
        return movies.stream().filter(combined).toList();
    }

    public List<Movie> listAllMovies(){
        return movies;
    }

    public Movie addNewMovie(String movieName, LocalDate date, String city, String theaterName){
        Movie movie = new Movie(movieId++, movieName, date, city, theaterName);
        movies.add(movie);
        return movie;
    }

}
