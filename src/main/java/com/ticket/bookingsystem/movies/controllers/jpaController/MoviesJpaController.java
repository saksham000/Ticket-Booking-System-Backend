package com.ticket.bookingsystem.movies.controllers.jpaController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.MovieRepo;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;

@RestController
public class MoviesJpaController {

    @Autowired
    private MovieRepo movieRepoService;

    @GetMapping(path = "movies")
    public List<Movie> listAllMovies() {
        return movieRepoService.findAll();
    }

    @GetMapping(path = "/movies/{movieName}/{city}")
    public List<Movie> findMovieByName(@PathVariable String movieName, @PathVariable String city) {

        if (!movieRepoService.findByMovieNameAndCity(movieName, city).isEmpty()) {
            return movieRepoService.findByMovieNameAndCity(movieName, city);
        } else {
            throw new MovieNotFoundException("Movie Name " + movieName + " And city name " + city + " Not Found");
        }
    }

    @GetMapping(path = "movies/{mId}")
    public Movie findMovieById(@PathVariable int mId) {
        if (movieRepoService.findById(mId).isPresent()) {
            return movieRepoService.findById(mId).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }

    @PostMapping(path = "movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepoService.save(movie);
    }

    @DeleteMapping(path = "movies/{mId}")
    public void deleteMovie(@PathVariable int mId) {
        if (movieRepoService.findById(mId).isPresent()) {
            movieRepoService.deleteById(mId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + mId + " not found");
        }
    }

}
