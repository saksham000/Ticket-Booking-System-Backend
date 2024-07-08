package com.ticket.bookingsystem.movies.databasefiles.jpaRepositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.bookingsystem.movies.databasefiles.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    
} 
