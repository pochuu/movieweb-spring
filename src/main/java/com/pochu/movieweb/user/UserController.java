package com.pochu.movieweb.user;

import com.pochu.movieweb.user.dto.NewUser;
import com.pochu.movieweb.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUser(@RequestBody NewUser user) {
        userService.addNewUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("userId") Long id){
        return userService.findUser(id);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyUser(@PathVariable("userId") Long id, @RequestBody User user){
        userService.modifyUser(id, user);
    }
}
