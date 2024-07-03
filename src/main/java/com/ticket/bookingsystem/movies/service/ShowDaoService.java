package com.ticket.bookingsystem.movies.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.bookingsystem.movies.databasefiles.Show;

@Service
public class ShowDaoService {
    private static List<Show> shows = new ArrayList<>();
    private static int showId = 10;

    static {

        shows.add(new Show(showId++, 0, LocalTime.of(12, 30)));
        shows.add(new Show(showId++, 1, LocalTime.of(3, 30)));
        shows.add(new Show(showId++, 1, LocalTime.of(6, 0)));
    }

    public List<Show> listAllShows() {
        return shows;
    }

    public List<Show> addShow(Show show) {
        show.setShowId(showId++);
        shows.add(show);
        return shows;
    }

    public List<Show> findShowByMovie(int movieId) {
        List<Show> movieShows = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovieIdShow() == movieId) {
                movieShows.add(show);
            }
        }
        return movieShows;
    }

}
