package com.app.Numerous.api;

import com.app.Numerous.model.Tweet;
import com.app.Numerous.model.User;
import com.app.Numerous.service.TweetService;
import com.app.Numerous.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class AppController {
    private final UserService userService;
    private final TweetService tweetService;

    @Autowired
    public AppController(UserService userService, TweetService tweetService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    //TWEETS
    @PostMapping("/tweets")
    public void addTweet(@Valid @NonNull @RequestBody Tweet tweet){ tweetService.addTweet(tweet); }

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping(path = "/tweets/{id}")
    public Tweet getTweetById(@PathVariable("id") UUID id) {
        return tweetService.getTweetById(id).orElse(null);
    }

    @DeleteMapping(path = "/tweets/{id}") //TODO Request user pw
    public void deleteTweetById(@PathVariable("id") UUID id) {
        tweetService.deleteTweetById(id);
    }

    //USERS
    @PostMapping("/users")
    public Boolean signUp(@Valid @NonNull @RequestBody User user){ return userService.addUser(user); }

    @GetMapping("/users")
    public List<User> getAllUsers(@NonNull @RequestBody AdminPassword adminPw) { return userService.getAllUsers(adminPw); }

    @DeleteMapping(path = "/users/{id}") //TODO Request user pw
    public void deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUserById(id);
    }

    @GetMapping(path = "/users/{id}")
    public List<Tweet> getAllTweetsOfUser(@PathVariable("id") UUID id) {
        return tweetService.getAllTweetsOfUser(id);
    }

    @PostMapping(path = "/login")
    public User login(@Valid @NonNull @RequestBody User user) { return userService.userLogin(user).orElse(null); }
}
