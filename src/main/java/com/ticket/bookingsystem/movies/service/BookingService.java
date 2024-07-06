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

    // public Seat bookSeat(int showId, int seatNumber, String userId) {
    //     Optional<Show> showOptional = showRepository.findShowById(showId);
    //     if (!showOptional.isPresent()) {
    //         throw new ShowNotFoundException("Show with ID " + showId + " does not exist.");
    //     }

    //     Show show = showOptional.get();
    //     Optional<Seat> seatOptional = show.getSeats().stream()
    //             .filter(seat -> seat.getSeatNumber() == seatNumber)
    //             .findFirst();

    //     if (!seatOptional.isPresent()) {
    //         throw new SeatNotFoundException("Seat with number " + seatNumber + " does not exist in show " + showId);
    //     }

    //     Seat seat = seatOptional.get();
    //     if (seat.isBooked()) {
    //         throw new SeatAlreadyBookedException("Seat " + seatNumber + " is already booked.");
    //     }

    //     seat.bookSeat(userId);
    //     return seat;
    // }

}
