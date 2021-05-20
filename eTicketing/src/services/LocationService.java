package services;

import entities.locations.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LocationService {
    public static Location addLocation(String location) {
        Scanner scanner = new Scanner(System.in);
        Location e = null;

        System.out.println("Location name: ");
        String name = scanner.nextLine();

        System.out.println("Address of the location: ");
        String address = scanner.nextLine();

        System.out.println("Contact: ");
        Phone contact = Service.addPhone();

        System.out.println("Maximum capacity: ");
        Integer capacity = scanner.nextInt();

        System.out.println("Standard price per hour: ");
        Integer price_per_hour = scanner.nextInt();

        System.out.println("Surface in square meters: ");
        Integer surface = scanner.nextInt();

        switch (location.toLowerCase()) {
            case "arena" -> {
                e = new Arena(name, address, contact, capacity, price_per_hour, surface);
                try {
                    WriteToCSV.writeToCSV(e, "./eTicketing/src/resources/arenas.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "outdoor" -> {
                e = new Outdoor(name, address, contact, capacity, price_per_hour, surface);
                try {
                    WriteToCSV.writeToCSV(e, "./eTicketing/src/resources/outdoors.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "theatre" -> {
                e = new Theatre(name, address, contact, capacity, price_per_hour, surface);
                try {
                    WriteToCSV.writeToCSV(e, "./eTicketing/src/resources/theatres.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return e;
    }

    public static List<Location> listSortedLocations(List<Location> l) {
        l.sort(Location::compareTo);
        for (int i = 0; i < Service.all_locations.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + Service.all_locations.get(i).getName());
        }
        return l;
    }
}
