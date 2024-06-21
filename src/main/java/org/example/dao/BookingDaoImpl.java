package org.example.dao;

import org.example.model.Booking;
import org.example.util.BookingSystemException;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements GenericDao<Booking, Integer> {

    @Override
    public void add(Booking booking) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "INSERT INTO Bookings (user_id, room_id, booking_date, start_time, end_time, number_of_occupants) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getUserId());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setDate(3, booking.getBookingDate());
            preparedStatement.setTime(4, booking.getStartTime());
            preparedStatement.setTime(5, booking.getEndTime());
            preparedStatement.setInt(6, booking.getNumberOfOccupants());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error adding booking", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement);
        }
    }

    @Override
    public Booking getById(Integer id) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Booking booking = null;
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Bookings WHERE booking_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setUserId(resultSet.getInt("user_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setStartTime(resultSet.getTime("start_time"));
                booking.setEndTime(resultSet.getTime("end_time"));
                booking.setNumberOfOccupants(resultSet.getInt("number_of_occupants"));
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting booking by ID", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return booking;
    }

    @Override
    public List<Booking> getAll() throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM Bookings";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setUserId(resultSet.getInt("user_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setStartTime(resultSet.getTime("start_time"));
                booking.setEndTime(resultSet.getTime("end_time"));
                booking.setNumberOfOccupants(resultSet.getInt("number_of_occupants"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error getting all bookings", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return bookings;
    }

    @Override
    public void update(Booking booking) throws BookingSystemException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "UPDATE Bookings SET user_id = ?, room_id = ?, booking_date = ?, start_time = ?, end_time = ?, number_of_occupants = ? WHERE booking_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getUserId());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setDate(3, booking.getBookingDate());
            preparedStatement.setTime(4, booking.getStartTime());
            preparedStatement.setTime(5, booking.getEndTime());
            preparedStatement.setInt(6, booking.getNumberOfOccupants());
            preparedStatement.setInt(7, booking.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error updating booking", e);
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
            String query = "DELETE FROM Bookings WHERE booking_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BookingSystemException("Error deleting booking", e);
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
            String query = "SELECT COUNT(*) AS count FROM Bookings WHERE booking_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Error checking existence of booking", e);
        } finally {
            DBUtil.closeResources(connection, preparedStatement, resultSet);
        }
        return exists;
    }
}
