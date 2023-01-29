package com.pochu.movieweb.movie;

import lombok.*;
import org.springframework.core.annotation.AliasFor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String title;
    private String producer;
    private Double rating;
    private String description;
    private Integer numOfRates;
}
