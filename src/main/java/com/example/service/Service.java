package com.example.service;

import com.example.exceptions.IncorrectInput;

import java.sql.SQLException;
import java.util.List;



public interface Service<T, Id>{
    T getById(Id id) throws SQLException;
    List<T> getAll() throws SQLException;
    void add(T obj) throws IncorrectInput, SQLException;
    void removeById(Id id) throws SQLException;
    void update(T obj) throws SQLException;
}
