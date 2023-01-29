package com.pochu.movieweb.movie;

import com.pochu.movieweb.movie.dto.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }
}
