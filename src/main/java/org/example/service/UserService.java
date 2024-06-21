package org.example.service;

import org.example.model.User;

import java.sql.SQLException;

public interface UserService {
    void registerUser(User user) throws SQLException;

    User getUser(int userId) throws SQLException;
}
