// RoomDao.java
package org.example.dao;

import org.example.model.Room;
import org.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public void addRoom(Room room) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO Rooms (room_name, capacity) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, room.getRoomName());
        preparedStatement.setInt(2, room.getCapacity());
        preparedStatement.executeUpdate();
    }

    public Room getRoomById(int roomId) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Rooms WHERE room_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Room room = new Room();
            room.setRoomId(resultSet.getInt("room_id"));
            room.setRoomName(resultSet.getString("room_name"));
            room.setCapacity(resultSet.getInt("capacity"));
            return room;
        }
        return null;
    }

    public boolean existsById(int userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE user_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }

        return false;
    }

    public List<Room> getAllRooms() throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Rooms";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Room room = new Room();
            room.setRoomId(resultSet.getInt("room_id"));
            room.setRoomName(resultSet.getString("room_name"));
            room.setCapacity(resultSet.getInt("capacity"));
            rooms.add(room);
        }
        return rooms;
    }

    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE Rooms SET room_name = ?, capacity = ? WHERE room_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setInt(3, room.getRoomId());
            preparedStatement.executeUpdate();
        }
    }

    // Other CRUD operations as needed
}
