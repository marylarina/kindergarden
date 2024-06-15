package com.example.controllers;

import java.sql.SQLException;
import java.util.List;

public interface Controller <T>{

    String Add(String... args) throws SQLException;

    String Delete(String Id) throws SQLException;

    String Update(String... args) throws SQLException;

    List<T> All() throws SQLException;
}
