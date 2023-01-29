package com.pochu.movieweb.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pochu.movieweb.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = true)
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Review> reviews;

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
