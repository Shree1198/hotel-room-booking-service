package org.example.util;

public class QueryConst {

    private QueryConst() {
    }

    public final static String ADD_BOOK = "INSERT INTO Bookings (user_id, room_id, booking_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
}
