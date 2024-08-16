package com.ticket.bookingsystem.movies.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.exceptions.MovieNotFoundException;

@Service
public class BookingService {

    @Autowired
    private ShowDaoService showServie;

    @Autowired
    private ShowDaoService showService;

    public List<Seat> getSeatForShow(int showId) {
        Optional<Show> show = showServie.findShowById(showId);
        return show.map(Show::getSeats).orElse(null);

    }

    public Seat bookSeat(int showId, int seatNumber, int userId){
        Optional<Show> showOptional = showService.findShowById(showId);


        if (!showOptional.isPresent()) {
            throw new MovieNotFoundException("Show with ID " + showId + " does not exist.");
        }

        Show show = showOptional.get();

        Optional<Seat> seatOptional = show.getSeats().stream().filter(seat -> seat.getSeatNo() == seatNumber).findFirst();


        if (!seatOptional.isPresent()) {
            throw new MovieNotFoundException("Seat with ID " + seatNumber + " does not exist.");
        }

        Seat seat= seatOptional.get();
        if(seat.isReserved()){
            throw new MovieNotFoundException("Seat with ID " + seatNumber + " is already reserved.");
        }
        
        seat.bookSeat(userId);
        return seat;
    }

}
