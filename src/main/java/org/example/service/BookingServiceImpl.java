package org.example.service;

import org.example.dao.BookingDaoImpl;
import org.example.dao.RoomDaoImpl;
import org.example.dao.UserDaoImpl;
import org.example.model.Booking;
import org.example.model.Room;
import org.example.util.BookingSystemException;

import java.sql.SQLException;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final BookingDaoImpl bookingDaoImpl;
    private final RoomDaoImpl roomDaoImpl;
    private final UserDaoImpl userDaoImpl;

    public BookingServiceImpl() {
        this.bookingDaoImpl = new BookingDaoImpl();
        this.roomDaoImpl = new RoomDaoImpl();
        this.userDaoImpl = new UserDaoImpl();
    }

//    public BookingServiceImpl(BookingDaoImpl bookingDaoImpl, RoomDaoImpl roomDaoImpl, UserDaoImpl userDaoImpl) {
//        this.bookingDaoImpl = bookingDaoImpl;
//        this.roomDaoImpl = roomDaoImpl;
//        this.userDaoImpl = userDaoImpl;
//    }

    @Override
    public void addBooking(Booking booking) throws BookingSystemException {
        // check user and room is present or not in dao
        // reduce the no of room
        // is present then book
//        System.out.println("Inside the implemented addBooking!!");
        try {
            boolean userPresent = userDaoImpl.existsById(booking.getUserId());
            if (userPresent) {
                Room room = roomDaoImpl.getById(booking.getRoomId());
                if (room != null && room.getCapacity() > booking.getNumberOfOccupants()) {
                    room.setCapacity(room.getCapacity() - booking.getNumberOfOccupants());
                    roomDaoImpl.update(room);
                    bookingDaoImpl.add(booking);

//                    if (true /* Condition to simulate failure */) {
//                        throw new SQLException("Simulated failure before adding booking");
//                    }

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
        return bookingDaoImpl.getAll();
    }

    @Override
    public Booking getBookingById(int bookingId) throws SQLException {
        return bookingDaoImpl.getById(bookingId);
    }

    // Implement other methods
}
