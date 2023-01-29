package com.pochu.movieweb.movie;

import com.pochu.movieweb.movie.dto.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query("SELECT m FROM movies m WHERE m.title = ?1")
    Optional<Movie> findMovieByTitle(String title);
    @Modifying
    @Transactional
    @Query("update movies m set m.rating = ?1, m.numOfRates = m.numOfRates + ?2 where m.id = ?3")
    void updateRatingByMovieId(Double rating, Long increment ,Long id);
}
