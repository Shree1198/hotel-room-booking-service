// BookingDao.java
package org.example.dao;

import org.example.model.Booking;
import org.example.util.BookingSystemException;
import org.example.util.DBUtil;
import org.example.util.QueryConst;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {


    public void addBooking(Booking booking) throws SQLException {
        Connection connection = DBUtil.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(QueryConst.ADD_BOOK);
        preparedStatement.setInt(1, booking.getUserId());
        preparedStatement.setInt(2, booking.getRoomId());
        preparedStatement.setDate(3, booking.getBookingDate());
        preparedStatement.setTime(4, booking.getStartTime());
        preparedStatement.setTime(5, booking.getEndTime());
        preparedStatement.executeUpdate();
    }

    public Booking getBookingById(int bookingId) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Bookings WHERE booking_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bookingId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Booking booking = new Booking();
            booking.setBookingId(resultSet.getInt("booking_id"));
            booking.setUserId(resultSet.getInt("user_id"));
            booking.setRoomId(resultSet.getInt("room_id"));
            booking.setBookingDate(resultSet.getDate("booking_date"));
            booking.setStartTime(resultSet.getTime("start_time"));
            booking.setEndTime(resultSet.getTime("end_time"));
            return booking;
        }
        return null;
    }

    public List<Booking> getBookingsByRoomAndDate(int roomId, Date date) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM Bookings WHERE room_id = ? AND booking_date = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        preparedStatement.setDate(2, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Booking> bookings = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setBookingId(resultSet.getInt("booking_id"));
            booking.setUserId(resultSet.getInt("user_id"));
            booking.setRoomId(resultSet.getInt("room_id"));
            booking.setBookingDate(resultSet.getDate("booking_date"));
            booking.setStartTime(resultSet.getTime("start_time"));
            booking.setEndTime(resultSet.getTime("end_time"));
            bookings.add(booking);
        }
        return bookings;
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Bookings";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setUserId(resultSet.getInt("user_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setStartTime(resultSet.getTime("start_time"));
                booking.setEndTime(resultSet.getTime("end_time"));
                bookings.add(booking);
            }
        }

        return bookings;
    }
}
