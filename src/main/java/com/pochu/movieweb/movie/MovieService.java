package com.pochu.movieweb.movie;

import com.pochu.movieweb.exception.ApiRequestException;
import com.pochu.movieweb.movie.dto.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository.findMovieByTitle(movie.getTitle());
        if (movieOptional.isPresent()){
            throw new ApiRequestException("Title already exists." ,HttpStatus.CONFLICT);
        }
        movieRepository.save(movie);
    }
}
