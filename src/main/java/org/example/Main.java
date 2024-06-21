package org.example;

import org.example.model.Booking;
import org.example.model.Room;
import org.example.model.User;
import org.example.service.*;
import org.example.util.BookingSystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, BookingSystemException {

        User user = new User();
        user.setEmail("sdfgs");
        user.setPassword("baba ji ka");
        user.setUsername("Mahesh A");

        new UserServiceImpl().registerUser(user);


        Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("Conference Room A");
        room.setCapacity(50);

        new RoomServiceImpl().addRoom(room);


        Booking booking = new Booking();
        booking.setStartTime(new Time(545634l));
        booking.setEndTime(new Time(545664l));
        booking.setUserId(1);
        booking.setRoomId(1);
        booking.setBookingDate(new Date(545634l));
        booking.setNumberOfOccupants(2);


        new BookingServiceImpl().addBooking(booking);

        BookingService bookingService = new BookingServiceImpl();

        try {
            List<Booking> bookings = bookingService.getAllBookings();
            for (Booking booking1 : bookings) {
                System.out.println("Booking ID: " + booking1.getBookingId());
                System.out.println("User ID: " + booking1.getUserId());
                System.out.println("Room ID: " + booking1.getRoomId());
                System.out.println("Booking Date: " + booking1.getBookingDate());
                System.out.println("Start Time: " + booking1.getStartTime());
                System.out.println("End Time: " + booking1.getEndTime());
                System.out.println("---------------------------");
            }
        } catch (BookingSystemException e) {
            e.printStackTrace();
            System.out.println("Error fetching bookings: " + e.getMessage());
        }
    }
}