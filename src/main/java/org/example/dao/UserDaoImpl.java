package org.example.dao;

import org.example.model.User;
import org.example.util.BookingSystemException;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements GenericDao<User, Integer> {

    @Override
    public void add(User user) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error adding user", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement);
        }
    }

    @Override
    public User getById(Integer id) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting user by ID", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Users";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting all users", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return users;
    }

    @Override
    public void update(User user) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "UPDATE Users SET username = ?, password = ?, email = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error updating user", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "DELETE FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error deleting user", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean existsById(Integer id) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT COUNT(*) AS count FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error checking existence of user", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return exists;
    }
}
