package com.ticket.bookingsystem.movies.databasefiles.jpaRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticket.bookingsystem.movies.databasefiles.Show;

public interface ShowRepo extends JpaRepository<Show, Integer> {

    @Query("SELECT s FROM Show s WHERE s.movieIdShow = :mId")
    List<Show> findShowsByMovieId(@Param("mId") int mId);

}
