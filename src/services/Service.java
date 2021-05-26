package services;

import entities.locations.*;
import entities.person.*;
import entities.events.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static Service single_instance = null;
    //lists updated in the corresponding class constructor:
    public static List<Actor> all_actors;
    public static List<Singer> all_singers;
    public static List<Location> all_locations;
    public static List<Event> all_events;
    public static FileWriter fileWriter;

    private Service() {
    }

    public static Service getInstance() {
        if (single_instance == null) {
            single_instance = new Service();
        }
        return single_instance;
    }

    // Method for manipulating a String into an Hour class object
    public static Hour stringToHour(String s) {
        Integer hour = Integer.parseInt(s.substring(0, 2));
        Integer minutes = Integer.parseInt(s.substring(3, 5));
        Hour h = new Hour(hour, minutes);
        return h;
    }

    public static Phone addPhone() {
        Scanner scanner = new Scanner(System.in);
        Phone p = new Phone(scanner.nextLine());
        return p;
    }

    public static Phone stringToPhone(String s){
        Phone p = new Phone(s);
        return p;
    }

    public static Client addClient(String client) {
        Scanner scanner = new Scanner(System.in);
        Client c = null;

        System.out.println("Enter the details of the ticket holder");

        System.out.println("First name: ");
        String f_name = scanner.nextLine();

        System.out.println("Last name: ");
        String l_name = scanner.nextLine();

        switch (client.toLowerCase()) {
            case "adult" -> {
                System.out.println("Phone number: ");
                Phone phone = addPhone();
                c = new Adult(f_name, l_name, phone);
                break;
            }
            case "child" -> {
                c = new Child(f_name, l_name);
                break;
            }
        }
        return c;
    }

    public static Organizer addOrganizer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name: ");
        String f_name = scanner.nextLine();

        System.out.println("Last name: ");
        String l_name = scanner.nextLine();

        System.out.println("Phone number: ");
        Phone phone = addPhone();
        Organizer o = new Organizer(f_name, l_name, phone);

        return o;
    }

    public static void initLists(){
        Service.all_singers = new ArrayList<>();
        Service.all_actors = new ArrayList<>();
        Service.all_locations = new ArrayList<>();
        Service.all_events = new ArrayList<>();
    }

}
