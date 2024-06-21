package org.example.dao;

import org.example.model.Room;
import org.example.util.BookingSystemException;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements GenericDao<Room, Integer> {

    @Override
    public void add(Room room) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "INSERT INTO Rooms (room_name, capacity) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error adding room", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement);
        }
    }

    @Override
    public Room getById(Integer id) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Room room = null;
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Rooms WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setRoomName(resultSet.getString("room_name"));
                room.setCapacity(resultSet.getInt("capacity"));
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting room by ID", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return room;
    }

    @Override
    public List<Room> getAll() throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Rooms";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setRoomName(resultSet.getString("room_name"));
                room.setCapacity(resultSet.getInt("capacity"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting all rooms", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return rooms;
    }

    @Override
    public void update(Room room) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "UPDATE Rooms SET room_name = ?, capacity = ? WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setInt(3, room.getRoomId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error updating room", e);
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
            String query = "DELETE FROM Rooms WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error deleting room", e);
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
            String query = "SELECT COUNT(*) AS count FROM Rooms WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error checking existence of room", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return exists;
    }
}
