package com.app.Numerous.dao;

import com.app.Numerous.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresql-tweets")
public class TweetDataAccess implements TweetDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TweetDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTweet(UUID id, Tweet tweet, String date) {
        String sql = "INSERT INTO tweets(id, user_id, user_number, text, post_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, tweet.getUser(), tweet.getUserNum(), tweet.getText(), date);
    }

    @Override
    public List<Tweet> selectAllTweets() {
        final String sql = "SELECT id, user_id, user_number, text, post_date FROM tweets";
        return jdbcTemplate.query(sql, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID user = UUID.fromString(resultSet.getString("user_id"));
            int user_num = Integer.parseInt(resultSet.getString("user_number"));
            String text = resultSet.getString("text");
            String date = resultSet.getString("post_date");

            return new Tweet(id, user, user_num, text, date);
        });
    }

    @Override
    public Optional<Tweet> selectTweetById(UUID target) {
        final String sql = "SELECT id, user_id, user_number, text, post_date FROM tweets WHERE id = ?";
        Tweet tweet = jdbcTemplate.queryForObject(sql, new Object[]{target}, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID user = UUID.fromString(resultSet.getString("user_id"));
            int user_num = Integer.parseInt(resultSet.getString("user_number"));
            String text = resultSet.getString("text");
            String date = resultSet.getString("post_date");

            return new Tweet(id, user, user_num, text, date);
        });
        return Optional.ofNullable(tweet);
    }

    @Override
    public int deleteTweetById(UUID id) {
        final String sql = "DELETE FROM tweets WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Tweet> selectAllTweetsOfUser(UUID target) {
        final String sql = "SELECT id, user_id, user_number, text, post_date FROM tweets WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{target}, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID user = UUID.fromString(resultSet.getString("user_id"));
            int user_num = Integer.parseInt(resultSet.getString("user_number"));
            String text = resultSet.getString("text");
            String date = resultSet.getString("post_date");

            return new Tweet(id, user, user_num, text, date);
        });
    }
}
