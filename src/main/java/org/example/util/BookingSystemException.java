package org.example.util;

import java.sql.SQLException;

public class BookingSystemException extends SQLException {
    public BookingSystemException(String message) {
        super(message);
    }

    public BookingSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
