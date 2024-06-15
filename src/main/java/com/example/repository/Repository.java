package com.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, Id> {

    T getById(Id id) throws SQLException;
    List<T> getAll() throws SQLException;
    void add(T obj) throws SQLException;
    void removeById(Id id) throws SQLException;
    void update(T obj) throws SQLException;
}
