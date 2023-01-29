package com.pochu.movieweb.movie.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pochu.movieweb.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "movies")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String producer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Review> review;
    private Double rating;
    private Integer numOfRates;

    public Movie(Long id, String title, String producer, Double rating, Integer numOfRates)
    {
        this.id = id;
        this.title = title;
        this.producer = producer;
        this.rating = rating;
        this.numOfRates = numOfRates;
    }
}
