package org.example.dao;

import org.example.util.BookingSystemException;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, ID> {
    
    void add(T entity) throws BookingSystemException;
    
    T getById(ID id) throws BookingSystemException;
    
    List<T> getAll() throws BookingSystemException;
    
    void update(T entity) throws BookingSystemException;
    
    void delete(ID id) throws BookingSystemException;
    
    boolean existsById(ID id) throws BookingSystemException;
    
    // Add more specific methods as needed for each entity DAO
    
}
