package org.example.service;

import org.example.dao.BookingDao;
import org.example.dao.RoomDao;
import org.example.dao.UserDao;
import org.example.model.Booking;
import org.example.model.Room;
import org.example.util.BookingSystemException;

import java.sql.SQLException;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private BookingDao bookingDao;
    private RoomDao roomDao;
    private UserDao userDao;

    public BookingServiceImpl() {
        bookingDao = new BookingDao();
        roomDao = new RoomDao();
        userDao = new UserDao();
    }

    @Override
    public void addBooking(Booking booking) throws BookingSystemException {
        // check user and room is present or not in dao
        // reduce the no of room
        // is present then book
        try {
            boolean userPresent = userDao.existsById(booking.getUserId());
            if (userPresent) {
                Room room = roomDao.getRoomById(booking.getRoomId());
                if (room != null && room.getCapacity() > booking.getNumberOfOccupants()) {
                    room.setCapacity(room.getCapacity() - booking.getNumberOfOccupants());
                    roomDao.updateRoom(room);
                    bookingDao.addBooking(booking);

                    if (true /* Condition to simulate failure */) {
                        throw new SQLException("Simulated failure before adding booking");
                    }

                } else {
                    throw new BookingSystemException("Room not available or insufficient capacity");
                }
            } else {
                throw new BookingSystemException("User not found");
            }
        } catch (SQLException e) {
            throw new BookingSystemException("Failed to add booking", e);
        }
    }

    @Override
    public List<Booking> getAllBookings() throws SQLException {
        return bookingDao.getAllBookings();
    }

    @Override
    public Booking getBookingById(int bookingId) throws SQLException {
        return bookingDao.getBookingById(bookingId);
    }

    // Implement other methods
}
