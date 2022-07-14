package com.company;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RidesController {

    private static void Menu() {

        System.out.println(
                        "\n\nChoose your action:\n" +
                        "\n1) show all rides" +
                        "\n2) add a ride" +
                        "\n3) delete ride by id" +
                        "\n4) search ride by id" +
                        "\n5) update ride by id" +
                        "\n6) show all rides date-ascending" +
                        "\n7) show a ride by date format:\"dd/mm/yy\"" +
                        "\n8) shutdown the program\n"
        );
    }

    public static void displayMenu() throws SQLException {

        boolean shutDown = false;

        Scanner s = new Scanner(System.in);
        Menu();

        while (!shutDown) {

            System.out.println("\n\taction: ");
            int action = s.nextInt();
            s.nextLine();

            switch (action) {
                case 1 -> MySQL.showAllRides();
                case 2 -> MySQL.addARide(newRideForm());
                case 3 -> MySQL.deleteRideById(rideIdForm());
                case 4 -> MySQL.searchRideById(rideIdForm());
                case 5 -> MySQL.updateRideById(rideIdForm(),newRideForm());
                case 6 -> MySQL.showAllRidesOrderByDates();
                case 7 -> MySQL.showRideByDateFormat(dateForm());
                case 8 -> { DatabaseConnection.getConnection().close(); System.exit(0); }

                default -> System.out.println("No such action");
            }
        }
    }

    public static String scanString(){
        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }
    public static Long scanLong(){
        Scanner s = new Scanner(System.in);
        return s.nextLong();

    }
    public static int scanInt(){
        Scanner s = new Scanner(System.in);
        return s.nextInt();

    }

    public static Ride newRideForm() {

        Ride ride = new Ride();

        System.out.println("\n - Enter new details - \n");

        System.out.println("Ride id number: ");
        ride.setRideId(scanLong());

        System.out.println("Comments: ");
        ride.setComments(scanString());

        System.out.println("Start point name: ");
        ride.setStartPointName(scanString());

        System.out.println("Destination: ");
        ride.setDestination(scanString());

        System.out.println("Ride time: ");
        ride.setRideTime(scanString());

        System.out.println("Back time: ");
        ride.setBackTime(scanString());

        System.out.println("Number of passengers: ");
        ride.setNumberOfPassengers(scanInt());

        System.out.println("Date: ");
        ride.setDate(scanString());

        return ride;
    }

    public static Ride rideIdForm() {

        Ride ride = new Ride();

        System.out.println("\n - Choose ride id - \n");

        System.out.println("ride id: ");
        ride.setRideId(scanLong());

        return ride;
    }

    public static Ride dateForm() {

        Ride ride = new Ride();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter the date of the ride you search by format dd/mm/yy : ");
        ride.setDate(String.format(scanString(), dateFormat));

        return ride;

    }
}