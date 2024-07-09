package com.ticket.bookingsystem.movies.controllers.jpaController;

import java.util.List;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.MovieRepo;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;
import com.ticket.bookingsystem.movies.service.jpaDaoService.MovieJpaDaoService;

@RestController
public class MoviesJpaController {

    @Autowired
    private MovieRepo movieRepoService;

    @Autowired
    private MovieJpaDaoService movieServiceJpa;

    @GetMapping(path = "movies")
    public List<Movie> listAllMovies() {
        return movieRepoService.findAll();
    }

    @GetMapping(path = "/movies/{movieName}/{city}")
    public List<Movie> findMovieByName(@PathVariable String movieName, @PathVariable String city) {
        try {
            return movieServiceJpa.findMovie(movieName, city);
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepoService.save(movie);
    }

}
