package com.company;

import java.sql.*;


public class DatabaseConnection {

    private static volatile Connection INSTANCE;


    private DatabaseConnection() {}


    public static Connection getConnection() throws SQLException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rides", "root", "root");
            if (INSTANCE == null) {
                synchronized (DatabaseConnection.class) {
                    if (INSTANCE == null) {
                        INSTANCE = con;
                    }
                }
            }

        } catch (SQLException e) {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("CREATE DATABASE rides");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rides", "root", "root");
            stmt = con.createStatement();
            stmt.execute("CREATE TABLE public_rides(rideId bigint PRIMARY KEY, comments VARCHAR(200), startPointName VARCHAR(200), destination VARCHAR(200), rideTime VARCHAR(200), backTime VARCHAR(200), numberOfPassengers INT, date VARCHAR(200))");

            if (INSTANCE == null) {
                synchronized (DatabaseConnection.class) {
                    if (INSTANCE == null) {
                        INSTANCE = con;
                    }
                }
            }
        }
        return INSTANCE;
    }
}