package org.example.util;

public class QueryConst {

    private QueryConst() {
    }

    public final static String ADD_BOOKING = "INSERT INTO Bookings (user_id, room_id, booking_date, start_time, end_time, number_of_occupants) VALUES (?, ?, ?, ?, ?, ?)";
    public final static String GET_BOOKING_BY_ID = "SELECT * FROM Bookings WHERE booking_id = ?";
    public final static String GET_ALL_BOOKINGS = "SELECT * FROM Bookings";
    public final static String UPDATE_BOOKING = "UPDATE Bookings SET user_id = ?, room_id = ?, booking_date = ?, start_time = ?, end_time = ?, number_of_occupants = ? WHERE booking_id = ?";
    public final static String DELETE_BOOKING = "DELETE FROM Bookings WHERE booking_id = ?";
    public final static String BOOKING_EXIST_BY_ID = "SELECT COUNT(*) AS count FROM Bookings WHERE booking_id = ?";
    public final static String ADD_ROOM = "INSERT INTO Rooms (room_name, capacity) VALUES (?, ?)";
    public final static String GET_ROOM_BY_ID = "SELECT * FROM Rooms WHERE room_id = ?";
    public final static String GET_ALL_ROOMS = "SELECT * FROM Rooms";
    public final static String UPDATE_ROOM = "UPDATE Rooms SET room_name = ?, capacity = ? WHERE room_id = ?";
    public final static String DELETE_ROOM = "DELETE FROM Rooms WHERE room_id = ?";
    public final static String ROOM_EXIST_BY_ID = "SELECT COUNT(*) AS count FROM Rooms WHERE room_id = ?";
    public final static String ADD_USER = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
    public final static String GET_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    public final static String GET_ALL_USER = "SELECT * FROM Users";
    public final static String UPDATE_USER = "UPDATE Users SET username = ?, password = ?, email = ? WHERE user_id = ?";
    public final static String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";
    public final static String USER_EXIST_BY_ID = "SELECT COUNT(*) AS count FROM Users WHERE user_id = ?";


}
