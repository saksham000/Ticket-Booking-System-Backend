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
import com.ticket.bookingsystem.movies.exceptions.ShowNotFoundException;

@Service
public class ShowDaoService {
    private static List<Show> shows = new ArrayList<>();

    @Autowired
    private MoviesDaoService movieService;

    private static int showId = 10;

    public ShowDaoService() {
        shows.add(new Show(showId++, 1, LocalTime.of(12, 30), 10));
        shows.add(new Show(showId++, 1, LocalTime.of(12, 30), 2));
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

    public List<Show> findShowByMovieId(int movieId) {
        Optional<Movie> movieOptional = movieService.findMovieById(movieId);
        List<Show> movieShows = new ArrayList<>();
        for (Show show : shows) {
            if (movieOptional.isPresent()) {
                movieShows.add(show);
            } else {
                throw new MovieNotFoundException("Movie with ID " + movieId + " does not exist");
            }
        }
        return movieShows;
    }

    public void deleteShow(int showid) {

        if (findShowById(showid).isPresent()) {
            Predicate<? super Show> predicate = show -> show.getShowId() == showid;
            shows.removeIf(predicate);
        } else {
            throw new ShowNotFoundException("Show with ID " + showid + " does not exist");
        }
    }

    public Optional<Show> findShowById(int sid) {
        Predicate<? super Show> prediate = show -> show.getShowId() == sid;

        Optional<Show> showOptional = shows.stream().filter(prediate).findFirst();
        if (!showOptional.isPresent()) {
            throw new ShowNotFoundException("Show with ID " + sid + " does not exist.");
        }
        return showOptional;
    }

}
