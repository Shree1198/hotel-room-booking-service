package org.example.service;

import org.example.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    void addRoom(Room room) throws SQLException;

    List<Room> getAllRooms() throws SQLException;

    Room getRoomById(int roomId) throws SQLException;

    void updateRoom(Room room) throws SQLException;

    // Other methods specific to room operations
}
