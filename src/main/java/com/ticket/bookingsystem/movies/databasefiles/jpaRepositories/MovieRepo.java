package com.ticket.bookingsystem.movies.databasefiles.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticket.bookingsystem.movies.databasefiles.Movie;
import java.util.List;


public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m WHERE m.movieName = :movieName AND m.city = :city")
    List<Movie> findByMovieNameAndCity(@Param("movieName") String movieName, @Param("city") String city);

    @Query("SELECT m FROM Movie m WHERE m.movieName = :movieName")
    List<Movie> findByMovieName(@Param("movieName") String movieName);
}
