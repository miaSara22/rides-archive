package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class MySQL {

    static Statement stmt;

    static {

        try {
            stmt = DatabaseConnection.getConnection().createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showAllRides() throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM rides.public_rides");

        if (rs.next())
            do {
                System.out.println("Ride id-> " + rs.getLong("rideId") + ", comments-> " + rs.getString("comments") + ", from-> " + rs.getString("startPointName") + ", to-> " + rs.getString("destination") + ", ride time-> " + rs.getString("rideTime") + ", back time-> " + rs.getString("backTime") + ", passengers-> " + rs.getInt("numberOfPassengers") + ", date-> " + rs.getString("date"));
            } while (rs.next());

        else {
            if (!rs.next())
                System.out.println("\nhmm...your rides archive is empty");

        }

        RidesController.displayMenu();
    }

    public static void addARide(Ride ride) throws SQLException {

        stmt.executeUpdate("INSERT IGNORE INTO rides.public_rides(rideId,comments,startPointName,destination,rideTime,backTime,numberOfPassengers,date) VALUES ("+ride.getRideId()+",'"+ride.getComments()+"','"+ride.getStartPointName()+"','"+ride.getDestination()+"','"+ride.getRideTime()+"','"+ride.getBackTime()+"',"+ride.getNumberOfPassengers()+",'"+ride.getDate()+"')");

        if (stmt.getUpdateCount() > 0) {
            System.out.println("\nAdded successfully");

        } else {
            System.out.println("\nFailed to add this ride. you can try again");

        }

        RidesController.displayMenu();

    }

    public static void deleteRideById(Ride ride) throws SQLException {

        stmt.executeUpdate("DELETE FROM rides.public_rides WHERE rideId= "+ride.rideId+"");

        if (stmt.getUpdateCount() > 0) {
            System.out.println("\nDeleted successfully");

        } else {
            System.out.println("\nFailed to delete this ride id. you can try again");

        }

        RidesController.displayMenu();

    }

    public static void searchRideById(Ride ride) throws SQLException { //done

        ResultSet rs = stmt.executeQuery("SELECT * FROM rides.public_rides WHERE rideId= "+ride.rideId+"");

        if (rs.next())
            do {
                System.out.println("Ride id-> " + rs.getLong("rideId") + ", comments-> " + rs.getString("comments") + ", from-> " + rs.getString("startPointName") + ", to-> " + rs.getString("destination") + ", ride time-> " + rs.getString("rideTime") + ", back time-> " + rs.getString("backTime") + ", passengers-> " + rs.getInt("numberOfPassengers") + ", date-> " + rs.getString("date"));
            } while (rs.next());

        else {
            if (!rs.next())
                System.out.println("\nhmm...couldn't find this ride id in the archive");

        }

        RidesController.displayMenu();

    }

    public static void updateRideById(Ride rideIdToUpdate, Ride ride) throws SQLException {

        try {

            stmt.executeUpdate("UPDATE rides.public_rides SET rideId="+ride.getRideId()+",comments='"+ride.getComments()+"',startPointName='"+ride.getStartPointName()+"',destination='"+ride.getDestination()+"',rideTime='"+ride.getRideTime()+"',backTime='"+ride.getBackTime()+"',numberOfPassengers="+ride.getNumberOfPassengers()+",date='"+ ride.getDate()+"' WHERE rideId="+rideIdToUpdate.getRideId()+"");

            if (stmt.getUpdateCount() > 0) {
                System.out.println("\nUpdated successfully");

            } else {
                System.out.println("\nFailed to update this ride id. you can try again");

            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nFailed. This ride id already exists!!!");
        }

        RidesController.displayMenu();

    }

    public static void showAllRidesOrderByDates() throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM rides.public_rides ORDER BY date ASC");

        if (rs.next())
            do {
                System.out.println("Ride id-> " + rs.getLong("rideId") + ", comments-> " + rs.getString("comments") + ", from-> " + rs.getString("startPointName") + ", to-> " + rs.getString("destination") + ", ride time-> " + rs.getString("rideTime") + ", back time-> " + rs.getString("backTime") + ", passengers-> " + rs.getInt("numberOfPassengers") + ", date-> " + rs.getString("date"));
            } while (rs.next());

        else {
            if (!rs.next())
                System.out.println("\nhmm...your rides archive is empty");

        }

        RidesController.displayMenu();
    }


    public static void showRideByDateFormat(Ride ride) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM rides.public_rides WHERE date LIKE '"+ride.getDate()+"'");

        if (rs.next())
            do {
                System.out.println("Ride id-> " + rs.getLong("rideId") + ", comments-> " + rs.getString("comments") + ", from-> " + rs.getString("startPointName") + ", to-> " + rs.getString("destination") + ", ride time-> " + rs.getString("rideTime") + ", back time-> " + rs.getString("backTime") + ", passengers-> " + rs.getInt("numberOfPassengers") + ", date-> " + rs.getString("date"));
            } while (rs.next());


        else {
            if (!rs.next())
                System.out.println("\nhmm...couldn't find this ride date in the archive");

        }

        RidesController.displayMenu();

    }
}