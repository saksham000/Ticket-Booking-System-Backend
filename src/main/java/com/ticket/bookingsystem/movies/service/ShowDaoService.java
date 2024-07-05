package com.ticket.bookingsystem.movies.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;

@Service
public class ShowDaoService {
    private static List<Show> shows = new ArrayList<>();

    @Autowired
    private MoviesDaoService movieService;
    private static int showId = 10;

    static {

        shows.add(new Show(showId++, 1, LocalTime.of(12, 30)));
    }

    public List<Show> listAllShows() {
        return shows;
    }

    public boolean addShow(Show show) {

        Optional<Movie> movie = movieService.findMovieById(show.getMovieIdShow());

        if (movie.isPresent()) {
            show.setShowId(showId++);
            shows.add(show);
            return true;
        } else {
            throw new MovieNotFoundException("Movie with ID " + show.getMovieIdShow() + " does not exist.");
        }

    }

    // public List<Show> addShow(Show show) {

    // show.setShowId(showId++);
    // shows.add(show);
    // return shows;
    // }

    public List<Show> findShowByMovie(int movieId) {
        List<Show> movieShows = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovieIdShow() == movieId) {
                movieShows.add(show);
            }
        }
        return movieShows;
    }

    public void deleteShow(int showid) {
        Predicate<? super Show> predicate = show -> show.getShowId() == showid;
        shows.removeIf(predicate);
    }

    public List<Show> listShowById(int sid) {
        Predicate<? super Show> prediate = show -> show.getShowId() == sid;
        List<Show> showById = new ArrayList<>();
        return showById.stream().filter(prediate).toList();
    }

}
