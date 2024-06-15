package com.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBService {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "kindergarden";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public Connection connection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL+DB_NAME, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
