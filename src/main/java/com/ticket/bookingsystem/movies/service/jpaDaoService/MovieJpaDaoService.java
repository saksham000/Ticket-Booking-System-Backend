package com.ticket.bookingsystem.movies.service.jpaDaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.MovieRepo;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;


@Service
public class MovieJpaDaoService {
    @Autowired
    private MovieRepo movieRepoService;


    
    public List<Movie> findMovie(String movieName, String city) {

        if (!movieRepoService.findByMovieNameAndCity(movieName, city).isEmpty()) {
            return movieRepoService.findByMovieNameAndCity(movieName, city);

        } else {
            throw new MovieNotFoundException("Movie Name " + movieName + " And city name " + city + " Not Found");
        }
    }
}
