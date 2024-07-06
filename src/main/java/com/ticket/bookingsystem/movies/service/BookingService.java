package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.databasefiles.User;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;

@Service
public class BookingService {

    @Autowired
    private ShowDaoService showServie;

    @Autowired
    private ShowDaoService showService;

    @Autowired
    private UserDaoService userService;

    public List<Seat> getSeatForShow(int showId) {
        Optional<Show> show = showServie.findShowById(showId);
        // return show.stream().map(Show::getSeat);
        return show.map(Show::getSeats).orElse(null);

    }

    public void setShowIdToSeat(int showid) {
        Optional<Show> showOptional = showService.findShowById(showid);
        if (!showOptional.isPresent()) {
            throw new MovieNotFoundException("Show with ID " + showid + " does not exist.");
        }
        Show show = showOptional.get();
        for (Seat seat : show.getSeats()) {
            seat.setShowId(showid);
        }
    }

    // public void setUserIdToSeat(int userid) {
    // Optional<User> userOptional = userService.findUserById(userid);
    // if (!userOptional.isPresent()) {
    // throw new MovieNotFoundException("Show with ID " + userid + " does not
    // exist.");
    // }
    // User user = userOptional.get();
    // for (Seat seat : user.getUserId()) {
    // seat.setUserId(userid);
    // }

    // }

    // public boolean bookSeat(String showId, int seatNumber, String userId) {
    // Optional<Show> show = showRepository.findShowById(showId);
    // if (show.isPresent()) {
    // Seat seat = show.get().getSeat(seatNumber);
    // if (seat != null && !seat.isBooked()) {
    // seat.bookSeat(userId);
    // return true;
    // }
    // }
    // return false;
    // }

}
