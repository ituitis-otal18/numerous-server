package com.app.Numerous.dao;

import com.app.Numerous.model.Tweet;
import com.app.Numerous.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresql-users")
public class UserDataAccess implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccess(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public int insertUser(UUID id, User user) {
        String sql = "INSERT INTO users(id, number, mail, password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, user.getNumber(), user.getMail(), user.getPassword());
    }

    @Override
    public List<User> selectAllUsers(){
        final String sql = "SELECT id, number, mail, password FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            int number = Integer.parseInt(resultSet.getString("number"));
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");

            return new User(id, number, mail, password);
        });
    }

    @Override
    public int deleteUserById(UUID id) {
        final String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> userLogin(User login){
        final String sql = "SELECT id, number, mail, password FROM users WHERE mail = ?";

        User result = jdbcTemplate.queryForObject(sql, new Object[]{login.getMail()}, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            int number = Integer.parseInt(resultSet.getString("number"));
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");

            return new User(id, number, mail, password);
        });

        if(result != null && result.getPassword().equals(login.getPassword())) return Optional.of(result);
        else return Optional.empty();
    }
}
