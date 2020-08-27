package com.app.Numerous.dao;

import com.app.Numerous.model.Tweet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TweetDao {

    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm dd-mm-yyyy");

    default int insertTweet(Tweet tweet){
        UUID id = UUID.randomUUID();
        String date = dateFormat.format(new Date());
        return insertTweet(id, tweet, date);
    };
    int insertTweet(UUID id, Tweet tweet, String date);

    List<Tweet> selectAllTweets();
    Optional<Tweet> selectTweetById(UUID id);
    List<Tweet> selectAllTweetsOfUser(UUID id);

    int deleteTweetById(UUID id);

}
