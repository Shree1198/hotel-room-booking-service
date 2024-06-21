package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDao();
    }

    @Override
    public void registerUser(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to register user", e);
        }
    }

    @Override
    public User getUser(int userId) {
        try {
            return userDao.getUserById(userId);
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to get user by ID", e);
        }
    }
}
