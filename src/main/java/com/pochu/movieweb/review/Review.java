package com.pochu.movieweb.review;

import com.pochu.movieweb.movie.dto.Movie;
import com.pochu.movieweb.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "reviews")
@Data
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id", referencedColumnName = "id")
    private User user;
    private String description;
    private Double rate;

    public Review(Long id, Movie movie, User user, String description, Double rate) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.description = description;
        this.rate = rate;
    }
}
