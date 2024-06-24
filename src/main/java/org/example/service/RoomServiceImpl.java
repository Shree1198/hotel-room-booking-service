package org.example.service;

import org.example.dao.RoomDao;
import org.example.dao.RoomDaoImpl;
import org.example.model.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final RoomDaoImpl roomDaoImpl;

    public RoomServiceImpl() {
        this.roomDaoImpl = new RoomDaoImpl();
    }

    @Override
    public void addRoom(Room room) {
        try {
            roomDaoImpl.add(room);
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to add room", e);
        }
    }

    @Override
    public List<Room> getAllRooms() {
        try {
            return roomDaoImpl.getAll();
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to retrieve all rooms", e);
        }
    }

    @Override
    public Room getRoomById(int roomId) {
        try {
            return roomDaoImpl.getById(roomId);
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to retrieve room by ID", e);
        }
    }

    @Override
    public void updateRoom(Room room) {
        try {
            roomDaoImpl.update(room);
        } catch (SQLException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to update room", e);
        }
    }

    // Implement other methods as per RoomService interface
}
