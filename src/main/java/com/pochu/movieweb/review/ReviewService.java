package com.pochu.movieweb.review;

import com.pochu.movieweb.exception.ApiRequestException;
import com.pochu.movieweb.movie.dto.Movie;
import com.pochu.movieweb.movie.MovieRepository;
import com.pochu.movieweb.review.dto.ReviewRequest;
import com.pochu.movieweb.user.User;
import com.pochu.movieweb.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
    public void addNewReview(ReviewRequest reviewRequest) {
        Optional<Review> reviewOptional = reviewRepository.findMovieReviewAddedByUser(reviewRequest.getUserId(), reviewRequest.getMovieId());
        Optional<User> userOptional = userRepository.findById(reviewRequest.getUserId());
        Optional<Movie> movieOptional = movieRepository.findById(reviewRequest.getMovieId());

        checkIfRecordExists(reviewOptional, userOptional, movieOptional);

        if (movieOptional.isPresent())
        {
            insertAverageMovieRating(movieOptional, 1L,reviewRequest);
        }
        if (!reviewOptional.isPresent()){
        Review review1 = Review.builder()
                .description(reviewRequest.getDescription())
                .rate(reviewRequest.getRate())
                .user(userOptional.get())
                .movie(movieOptional.get())
                .build();

        reviewRepository.save(review1);
        }
    }

    private void checkIfRecordExists(Optional<Review> reviewOptional, Optional<User> user, Optional<Movie> movie) {
        if (reviewOptional.isPresent()){
            throw new ApiRequestException("Review already exists." , HttpStatus.CONFLICT);
        }
        if(!user.isPresent())
        {
            throw new ApiRequestException("User does not exist." , HttpStatus.NOT_FOUND);
        }
        if(!movie.isPresent())
        {
            throw new ApiRequestException("Movie does not exist." , HttpStatus.NOT_FOUND);
        }
    }
    private void checkIfUserAndMovieExist( Optional<User> user, Optional<Movie> movie) {
        if(!user.isPresent())
        {
            throw new ApiRequestException("User does not exist." , HttpStatus.NOT_FOUND);
        }
        if(!movie.isPresent())
        {
            throw new ApiRequestException("Movie does not exist." , HttpStatus.NOT_FOUND);
        }
    }

    private void insertAverageMovieRating(Optional<Movie> movie,Long increment, ReviewRequest review) {
        Optional<List<Review>> reviewsByMovieId = reviewRepository.findReviewsByMovieId(movie.get().getId());
        reviewsByMovieId.get().removeIf(row -> row.getUser().getId().equals(review.getUserId()));
        Double sumOfAllReviews = 0.0;
        Long count = 1L;
        if (!reviewsByMovieId.get().isEmpty()){
         sumOfAllReviews = reviewsByMovieId.get().stream().mapToDouble(row -> row.getRate()).sum();
         count = reviewsByMovieId.get().stream().count() + 1;
        }
      movieRepository.updateRatingByMovieId((sumOfAllReviews + review.getRate()) / count,  increment,movie.get().getId());
    }


    public void deleteReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()){
            downgradeRating(reviewOptional);
            reviewOptional.get().setUser(null);
            reviewOptional.get().setMovie(null);
        }
        else {
            throw new ApiRequestException("Review doesn't exist",HttpStatus.NOT_FOUND);
        }
        reviewRepository.delete(reviewOptional.get());
    }

    private void downgradeRating(Optional<Review> reviewOptional) {
        Optional<List<Review>> reviewsByMovieId = reviewRepository.findReviewsByMovieId(reviewOptional.get().getMovie().getId());
        Double sumOfAllReviews = 0.0;
        Long count = 1L;
        if (!reviewsByMovieId.get().isEmpty()){
            sumOfAllReviews = reviewsByMovieId.get().stream().mapToDouble(row -> row.getRate()).sum();
            count = reviewsByMovieId.get().stream().count() + 1;
        }
        sumOfAllReviews -= reviewOptional.get().getRate();
        count -= 1;
        if (count>0) {
            sumOfAllReviews = sumOfAllReviews/count;
        }
        movieRepository.updateRatingByMovieId(sumOfAllReviews, -1l,reviewOptional.get().getMovie().getId());
    }

    public void updateReview(Long id, ReviewRequest reviewRequest) {
        Optional<Review> reviewOptional = reviewRepository.findMovieReviewAddedByUser(reviewRequest.getUserId(), reviewRequest.getMovieId());
        Optional<User> userOptional = userRepository.findById(reviewRequest.getUserId());
        Optional<Movie> movieOptional = movieRepository.findById(reviewRequest.getMovieId());

        checkIfUserAndMovieExist(userOptional, movieOptional);


        if (movieOptional.isPresent())
        {
            insertAverageMovieRating(movieOptional, 0L,reviewRequest);
        }

        if (reviewOptional.isPresent()){
            reviewOptional.get().setDescription(reviewRequest.getDescription());
            reviewOptional.get().setRate(reviewRequest.getRate());
            reviewOptional.get().setUser(userOptional.get());
            reviewOptional.get().setDescription(reviewRequest.getDescription());

            reviewRepository.save(reviewOptional.get());
        }
    }

    public Review findReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (!reviewOptional.isPresent()) {
            throw new ApiRequestException("Review doesn't exist", HttpStatus.NOT_FOUND);
        }

        return reviewOptional.get();
    }
}