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

import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.MovieRepo;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.ShowRepo;

@RestController
public class ShowJpaController {

    @Autowired
    private ShowRepo showRepoService;

    @Autowired
    private MovieRepo movieRepoService;

    @GetMapping(path = "shows")
    public List<Show> listAllShows() {
        return showRepoService.findAll();
    }

    @PostMapping(path = "shows")
    public void addShowToSpecificMovie(@RequestBody Show show) {

        if (movieRepoService.findById(show.getMovieIdShow()).isPresent()) {
            Show shows = new Show(show.getMovieIdShow(), show.getShowStart(), show.getNumberOfSeats());
            shows.setNumberOfSeats(show.getNumberOfSeats());
            showRepoService.save(shows);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Movie with id " + show.getMovieIdShow() + " is not present");
        }
    }

    @GetMapping(path = "shows/{mId}")
    public List<Show> getShowsOfMovie(@PathVariable int mId) {

        if (movieRepoService.findById(mId).isPresent()) {
            return showRepoService.findShowsByMovieId(mId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + mId + " is not present");
        }
    }

    @DeleteMapping(path = "shows/{sid}")
    public void deleteShow(@PathVariable int sid) {
        if (showRepoService.findById(sid).isPresent()) {
            showRepoService.deleteById(sid);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show with id " + sid + " is not Present");
        }
    }

    @GetMapping(path = "shows/showid/{sid}")
    public Show getShowById(@PathVariable int sid) {
        if (showRepoService.findById(sid).isPresent()) {
            return showRepoService.findById(sid).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show with id " + sid + " is not Present");
        }

    }
}
