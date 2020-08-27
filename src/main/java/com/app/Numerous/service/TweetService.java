package com.app.Numerous.service;

import com.app.Numerous.dao.TweetDao;
import com.app.Numerous.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TweetService {

    private final TweetDao tweetDao;

    @Autowired
    public TweetService(@Qualifier("postgresql-tweets") TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    public int addTweet(Tweet tweet){
        return tweetDao.insertTweet(tweet);
    }

    public List<Tweet> getAllTweets() { return tweetDao.selectAllTweets(); }

    public Optional<Tweet> getTweetById(UUID id) {
        return tweetDao.selectTweetById(id);
    }

    public int deleteTweetById(UUID id) {
        return tweetDao.deleteTweetById(id);
    }

    public List<Tweet> getAllTweetsOfUser(UUID id){ return tweetDao.selectAllTweetsOfUser(id); }

}
