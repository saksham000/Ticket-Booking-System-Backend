package com.ticket.bookingsystem.movies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;
import com.ticket.bookingsystem.movies.service.MoviesDaoService;

@RestController
public class MoviesController {
    MoviesDaoService movieService;

    public MoviesController(MoviesDaoService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "movies")
    public List<Movie> listAllMovies() {
        return movieService.listAllMovies();
    }

    @GetMapping(path = "movies/{movieName}/{city}")
    public Movie findMovieByName(@PathVariable String movieName, @PathVariable String city) {

        try {
            return movieService.findMovie(movieName, city);
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "movies")
    public Movie addMovie(@RequestBody Movie movie) {
        Movie addMovie = movieService.addNewMovie(movie.getMovieName(), movie.getDate(), movie.getCity(),
                movie.getTheatreName());
        return addMovie;
    }

}
