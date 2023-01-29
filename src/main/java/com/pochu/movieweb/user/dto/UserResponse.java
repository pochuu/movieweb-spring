package com.pochu.movieweb.user.dto;


import com.pochu.movieweb.movie.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class UserResponse{
        private Long id;
        private String name;
        private List<MovieDTO> listOfReviews;
}