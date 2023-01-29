package com.pochu.movieweb.user;

import com.pochu.movieweb.exception.ApiRequestException;
import com.pochu.movieweb.review.Review;
import com.pochu.movieweb.review.ReviewRepository;
import com.pochu.movieweb.movie.MovieDTO;
import com.pochu.movieweb.user.dto.NewUser;
import com.pochu.movieweb.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void addNewUser(NewUser userDTO) {
        Optional<User> userOptional = userRepository.findUserByName(userDTO.getName());

        if (userOptional.isPresent()){
            throw new ApiRequestException("User already exists." , HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setName(userDTO.getName());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);

        if (!exists){
            throw new ApiRequestException("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    public UserResponse findUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new ApiRequestException("User does not exist." , HttpStatus.NOT_FOUND);
        }
        Optional<List<Review>> reviewsList = reviewRepository.findAllReviewsByUserId(id);

        UserResponse userResponse = UserResponse.builder()
                .listOfReviews(reviewsList.get().stream().map(
                        review -> MovieDTO.builder()
                                .title(review.getMovie().getTitle())
                                .producer(review.getMovie().getProducer())
                                .rating(review.getRate())
                                .description(review.getDescription())
                                .build()).toList())
                .id(userOptional.get().getId())
                .name(userOptional.get().getName())
                .build();
        return userResponse;
    }

    public void modifyUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ApiRequestException("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        userOptional.get().setName(user.getName());

        userRepository.save(userOptional.get());

    }
}
