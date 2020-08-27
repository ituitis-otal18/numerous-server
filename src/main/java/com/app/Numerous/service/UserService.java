package com.app.Numerous.service;

import com.app.Numerous.api.AdminPassword;
import com.app.Numerous.dao.UserDao;
import com.app.Numerous.model.Tweet;
import com.app.Numerous.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;
    private final List<User> AllUsers;

    @Autowired
    public UserService(@Qualifier("postgresql-users") UserDao userDao) {
        this.userDao = userDao;
        this.AllUsers = this.userDao.selectAllUsers();
    }

    public Boolean addUser(User user){
        for(User u : AllUsers) if(u.getNumber() == user.getNumber() || u.getMail().equals(user.getMail())) return false;
        userDao.insertUser(user);
        AllUsers.add(user);
        return true;
    }

    public List<User> getAllUsers(AdminPassword adminPw){
        if(adminPw.check()) return userDao.selectAllUsers();
        else return null;
    }

    public int deleteUserById(UUID id) { return userDao.deleteUserById(id); }

    public Optional<User> userLogin(User user){ return userDao.userLogin(user); }
}
