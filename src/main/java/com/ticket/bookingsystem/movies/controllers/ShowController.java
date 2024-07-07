package com.ticket.bookingsystem.movies.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;
import com.ticket.bookingsystem.movies.service.ShowDaoService;

@RestController
public class ShowController {

    @Autowired
    private ShowDaoService showService;

    @GetMapping(path = "shows")
    public List<Show> listAllShows() {
        return showService.listAllShows();
    }

    @PostMapping(path = "shows")
    public void addShowToSpecificMovie(@RequestBody Show show) {
        try {
            showService.addShow(show);
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "shows/{mId}")
    public List<Show> getShowsOfMovie(@PathVariable int mId) {
        try{
            return showService.findShowByMovieId(mId);
        }catch(MovieNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        
    }

    @DeleteMapping(path = "shows/{sid}")
    public void deleteShow(@PathVariable int sid) {
        showService.deleteShow(sid);
    }

    @GetMapping(path = "shows/showid/{sid}")
    public Optional<Show> getShowById(@PathVariable int sid) {
        try {
            return showService.findShowById(sid);
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
