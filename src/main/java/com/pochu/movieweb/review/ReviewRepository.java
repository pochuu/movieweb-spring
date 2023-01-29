package com.pochu.movieweb.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM reviews r WHERE r.user.id = ?1 AND r.movie.id = ?2")
    Optional<Review> findMovieReviewAddedByUser(Long userId, Long movieId);
    @Query("SELECT r FROM reviews r WHERE r.user.id = ?1")
    Optional<List<Review>> findAllReviewsByUserId(Long userId);
    @Query("Select r FROM reviews r WHERE r.movie.id = ?1")
    Optional<List<Review>> findReviewsByMovieId(Long movieId);
}
