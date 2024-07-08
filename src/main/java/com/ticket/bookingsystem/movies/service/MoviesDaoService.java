package com.ticket.bookingsystem.movies.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;

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

    public Movie findMovie(String movieName, String city) {
        Optional<Movie> movieOptional = findMovieByNameAndCity(movieName, city);

        if (!movieOptional.isPresent()) {
            throw new MovieNotFoundException("Movie name " + movieName + " is Not found");
        } else {
            return movieOptional.get();
        }

    }

    public Optional<Movie> findMovieByNameAndCity(String movieName, String city) {
        return movies.stream().filter(movie -> movie.getMovieName().equalsIgnoreCase(movieName) && movie.getCity()
                .equalsIgnoreCase(city)).findFirst();
    }

    public List<Movie> listAllMovies() {
        return movies;
    }

    public Optional<Movie> findMovieById(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst();
    }

    public Movie addNewMovie(String movieName, LocalDate date, String city, String theaterName) {
        Movie movie = new Movie(movieId++, movieName, date, city, theaterName);
        movies.add(movie);
        return movie;
    }

}
