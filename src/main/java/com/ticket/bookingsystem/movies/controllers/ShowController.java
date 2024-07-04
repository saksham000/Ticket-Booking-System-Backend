package com.ticket.bookingsystem.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ticket.bookingsystem.movies.databasefiles.Show;
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
        showService.addShow(show);
    }

    @GetMapping(path = "shows/{mId}")
    public List<Show> getShowsOfMovie(@PathVariable int mId) {
        return showService.findShowByMovie(mId);
    }

    @DeleteMapping(path = "shows/{sid}")
    public void deleteShow(@PathVariable int sid) {
        showService.deleteShow(sid);
    }

}
