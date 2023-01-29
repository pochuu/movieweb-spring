package com.pochu.movieweb;

import com.pochu.movieweb.movie.dto.Movie;
import com.pochu.movieweb.movie.MovieRepository;
import com.pochu.movieweb.review.ReviewRepository;
import com.pochu.movieweb.user.User;
import com.pochu.movieweb.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitRecords {

    @Bean
    CommandLineRunner getCommandLineRunner(
            UserRepository userRepository,
            ReviewRepository reviewRepository,
            MovieRepository movieRepository
    ) {
     return args -> {
         User user1 = new User(1l, "Jonasz");
         User user2 = new User(2l, "Maciej");
         Movie movie = new Movie(1l, "Gladiator","Ridley Scott", 0.0,0);
         userRepository.saveAll(List.of(user1,user2));
         movieRepository.save(movie);
     };
    }
}
