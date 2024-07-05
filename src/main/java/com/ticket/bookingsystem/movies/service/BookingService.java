package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.databasefiles.Show;

@Service
public class BookingService {

    @Autowired
    private ShowDaoService showServie;

    public List<Seat> getSeatForShow(int showId){
        Optional<Show> show = showServie.findShowById(showId);
        // return show.stream().map(Show::getSeat);
        return show.map(Show::getSeats).orElse(null);

    }




    // public boolean bookSeat(String showId, int seatNumber, String userId) {
    //     Optional<Show> show = showRepository.findShowById(showId);
    //     if (show.isPresent()) {
    //         Seat seat = show.get().getSeat(seatNumber);
    //         if (seat != null && !seat.isBooked()) {
    //             seat.bookSeat(userId);
    //             return true;
    //         }
    //     }
    //     return false;
    // }


}
