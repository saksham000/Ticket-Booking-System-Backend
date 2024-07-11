package com.ticket.bookingsystem.movies.service.jpaDaoService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticket.bookingsystem.movies.databasefiles.Seat;
import com.ticket.bookingsystem.movies.databasefiles.Show;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.SeatRepo;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.ShowRepo;
import com.ticket.bookingsystem.movies.databasefiles.jpaRepositories.UserDataRepo;
import com.ticket.bookingsystem.movies.exceptions.SeatAlreadyBookedException;
import com.ticket.bookingsystem.movies.exceptions.SeatNotFoundException;
import com.ticket.bookingsystem.movies.exceptions.ShowNotFoundException;
import com.ticket.bookingsystem.movies.exceptions.UserNotFoundException;

@Service
public class BookingJpaService {

    @Autowired
    private ShowRepo showRepoService;

    @Autowired
    private SeatRepo seatRepoService;
    
    @Autowired
    private UserDataRepo userDataRepoService;

    public List<Seat> getSeatForShow(int showId) {
        Optional<Show> show = showRepoService.findById(showId);
        return show.map(Show::getSeats).orElse(null);

    }

    public Seat bookSeat(int showId, int seatNumber, int userId) {

        if(!userDataRepoService.findById(userId).isPresent()){
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }


        // Find the show by ID
        Show show = showRepoService.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id: " + showId));

        // Find the seat in the show by seat number
        Seat seat = show.getSeats().stream()
                .filter(s -> s.getSeatNo() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException("Seat not found with number: " + seatNumber));

        // Check if the seat is already booked
        if (seat.getuserId() != null) {
            throw new SeatAlreadyBookedException("Seat is already booked Seat Id: " + seat.getSeatNo());
        }

        // Book the seat by setting the user ID and make it true
        seat.setUserId(userId);
        seat.setIsReserved(true);

        return seatRepoService.save(seat);
    }

}
