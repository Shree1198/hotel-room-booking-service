package org.example.service;

import org.example.model.Booking;
import org.example.util.BookingSystemException;

import java.sql.SQLException;
import java.util.List;

public interface BookingService {
    void addBooking(Booking booking) throws BookingSystemException;

    List<Booking> getAllBookings() throws SQLException;

    Booking getBookingById(int bookingId) throws SQLException;
    // Add other methods as needed
}
