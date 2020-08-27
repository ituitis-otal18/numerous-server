package com.app.Numerous.dao;

import com.app.Numerous.model.Tweet;
import com.app.Numerous.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    };
    int insertUser(UUID id, User user);

    List<User> selectAllUsers();

    int deleteUserById(UUID id);

    Optional<User> userLogin(User user);
}
