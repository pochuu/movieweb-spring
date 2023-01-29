package com.pochu.movieweb.review;

import com.pochu.movieweb.review.dto.ReviewRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Review> getAllReviews(){
        return reviewService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.addNewReview(reviewRequest);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable("reviewId") Long id){
        reviewService.deleteReview(id);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@PathVariable("reviewId") Long id,@RequestBody ReviewRequest reviewRequest){
        reviewService.updateReview(id, reviewRequest);
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Review getReview(@PathVariable("reviewId") Long id){
        return reviewService.findReviewById(id);
    }

}
