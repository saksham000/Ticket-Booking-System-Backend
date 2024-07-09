package com.ticket.bookingsystem.movies.databasefiles.jpaRepositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.bookingsystem.movies.databasefiles.UserData;

public interface UserDataRepo extends JpaRepository<UserData, Integer> {

} 
