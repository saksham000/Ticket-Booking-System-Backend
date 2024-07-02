package com.ticket.bookingsystem.movies;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {
    MoviesDaoService movieService;

    public MoviesController(MoviesDaoService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "listmovies")
    public List<Movie> listAllMovies() {
        return movieService.listAllMovies();
    }

    @GetMapping(path = "findmovies/{movieName}/{city}")
    public List<Movie> findMovieByName(@PathVariable String movieName, @PathVariable String city) {
        return movieService.findMovieByName(movieName, city);
    }

    @PostMapping(path = "addmovies")
    public Movie addMovie(@RequestBody Movie movie) {
        Movie addMovie = movieService.addNewMovie(movie.getMovieName(), movie.getDate(), movie.getCity(),
                movie.getTheatreName());
        return addMovie;
    }

}
