package org.example.util;

public class ExceptionHandler {
    public static void handle(Exception e) {
        if (e instanceof BookingSystemException) {
            System.err.println("Booking System Error: " + e.getMessage());
        } else {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        e.printStackTrace();
    }
}
