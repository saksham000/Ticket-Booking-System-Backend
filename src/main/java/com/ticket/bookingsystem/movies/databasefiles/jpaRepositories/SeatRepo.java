package com.ticket.bookingsystem.movies.databasefiles.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.bookingsystem.movies.databasefiles.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

    
} 
