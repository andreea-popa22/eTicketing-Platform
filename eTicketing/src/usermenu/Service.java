package usermenu;

import locations.*;
import person.*;
import tickets.*;
import events.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static Service single_instance = null;
    public static List<Actor> all_actors;
    public static List<Singer> all_singers;
    public static List<Location> all_locations;

    private Service() {} ;

    public static Service getInstance() {
        if (single_instance == null){
            single_instance = new Service();
        }
        return single_instance;
    }

    // Method for manipulating a String into an Hour class object
    public static Hour stringToHour(String s){
        Integer hour = (Integer)(s.charAt(0) + s.charAt(1));
        Integer minutes = (Integer)(s.charAt(3) + s.charAt(4));
        Hour h = new Hour(hour, minutes);
        return h;
    }

    public static Phone addPhone() {
        Scanner scanner = new Scanner(System.in);
        Phone p = new Phone(scanner.nextLine());
        return p;
    }

    public static Singer addSinger() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Singer's name: ");
        String name  = scanner.nextLine();

        System.out.println("Price per hour: ");
        Integer price_per_hour = scanner.nextInt();

        System.out.println("Music type: ");
        String type = scanner.nextLine().toUpperCase();
        SingerType stype = SingerType.valueOf(type);

        Singer s = new Singer(name, price_per_hour, stype);  //Calling parameterized constructor so that the list of singers would be updated
        return s;
    }

    public static Actor addActor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Singer's name: ");
        String name = scanner.nextLine();

        System.out.println("Price per play: ");
        Integer price_per_play = scanner.nextInt();

        Actor a = new Actor(name, price_per_play);
        return a;
    }

    public static Location addLocation(String location) {
        Scanner scanner = new Scanner(System.in);
        Location e = null;

        System.out.println("Address of the location: ");
        String address = scanner.nextLine();

        System.out.println("Contact: ");
        Phone contact = addPhone();

        System.out.println("Maximum capacity: ");
        Integer capacity = scanner.nextInt();

        System.out.println("Standard price per hour: ");
        Integer price_per_hour = scanner.nextInt();

        System.out.println("Surface in square meters: ");
        Integer surface = scanner.nextInt();

        switch (location.toLowerCase()) {
            case "arena" -> {
                e = new Arena(address, contact, capacity, price_per_hour, surface);
                break;
            }
            case "outdoor" -> {
                e = new Outdoor(address, contact, capacity, price_per_hour, surface);
                break;
            }
            case "theatre" -> {
                e = new Theatre(address, contact, capacity, price_per_hour, surface);
                break;
            }
        }

        return e;
    }

    public static void listAllSingers(){
        for (int i = 0; i < Singer.singers; i++) {
            int index = i+1;
            System.out.println( index + ". " + all_singers.get(i).getName()); //print the name of the singer
        }
    }

    public static void listAllActors(){
        for (int i = 0; i < Actor.actors; i++) {
            int index = i+1;
            System.out.println( index + ". " + all_actors.get(i).getName()); //print the name of the singer
        }
    }

    public static List<Singer> sortSingersByName(List<Singer> l) {
        l.sort(Comparator.comparing(Singer::getName));
        return l;
    }

    public static List<Actor> sortActorsByName(List<Actor> l) {
        l.sort(Comparator.comparing(Actor::getName));
        return l;
    }

    public Concert addConcert() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the concert: ");
        String name = scanner.nextLine();

        System.out.println("Date of the event: ");
        String date = scanner.nextLine();

        System.out.println("Start time (hh:mm): ");
        Hour start_time = Service.stringToHour(scanner.nextLine());

        System.out.println("End time (hh:mm): ");
        Hour end_time = Service.stringToHour(scanner.nextLine());

        System.out.println("These are the existing singers in alphabetical order: ");
        all_singers = sortSingersByName(all_singers);
        listAllSingers();
        List sin = new ArrayList();
        System.out.println("Which singers from this list do you want to add? (ex: 1 3 7 23 / you can leave it blank): ");
        String indexes = scanner.nextLine();
        String[] ind = indexes.split(" ");
        for (int i = 0; i < ind.length; i++) {
            int index = Integer.parseInt(ind[i]) - 1;
            sin.add(all_singers.get(index)); //add the singer of the current index in the concert singers list
        }
        System.out.println("How many new singers do you want to add?: ");
        int no_of_new_singers = scanner.nextInt();
        for (int i = 0; i < no_of_new_singers; i++){
            sin.add(addSinger());
        }

        System.out.println("Do you want to:");
        System.out.println("1. Add a new location");
        System.out.println("2. Use an existing one ");
        int choice = scanner.nextInt();
        Location location = null;
        if (choice == 1) {
            System.out.println("What kind of location do you want to add? (Arena / Outdoor / Theatre");
            String choice_l = scanner.nextLine().toLowerCase();
            if (choice_l == "arena") {
                location = (Arena) addLocation(choice_l);
            }
            else if (choice_l == "outdoor") {
                location = (Outdoor) addLocation(choice_l);
            }
            else {
                location = (Theatre) addLocation(choice_l);
            }
        }
        else if (choice == 2) {
            System.out.println("In which order do you want the existing locations to be printed? 1. Alphabetical order 2. Ascending by price per hour");
            Integer choice_l = scanner.nextInt();
            if (choice_l == 1) {
                //;
            }
            else if (choice_l == 2) {
                //;
            }
            else {
                //Eroare try catch
                System.out.println("Invalid choice!");
            }
        }
        else {
            //Eroare try catch
            System.out.println("Invalid choice!");
        }

        //this.organizer = eu
        Organizer organizer = null;

        System.out.println("What is the standard price of the event ticket?");
        Integer ticket_price = scanner.nextInt();

        Concert c = new Concert(name, date, start_time, end_time, sin, location, organizer, ticket_price);
        return c;

    }

    public Play addPlay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the Play: ");
        String name = scanner.nextLine();

        System.out.println("Theme of the play (COMEDY / TRAGEDY / HISTORICAL / MUSICAL)");
        String t = scanner.nextLine().toUpperCase();
        PlayType theme = PlayType.valueOf(t);

        System.out.println("These are the existing actors in alphabetical order: ");
        all_actors = sortActorsByName(all_actors);
        listAllSingers();
        List act = new ArrayList();
        System.out.println("Which actors from this list do you want to add? (ex: 1 3 7 23 / you can leave it blank): ");
        String indexes = scanner.nextLine();
        String[] ind = indexes.split(" ");
        for (int i = 0; i < ind.length; i++) {
            int index = Integer.parseInt(ind[i]) - 1;
            act.add(all_actors.get(index)); //add the actor of the current index in the play actors list
        }
        System.out.println("How many new actors do you want to add?: ");
        int no_of_new_singers = scanner.nextInt();
        for (int i = 0; i < no_of_new_singers; i++){
            act.add(addActor());
        }

        System.out.println("Date of the event: ");
        String date = scanner.nextLine();

        System.out.println("Start time (hh:mm): ");
        Hour start_time = Service.stringToHour(scanner.nextLine());

        System.out.println("End time (hh:mm): ");
        Hour end_time = Service.stringToHour(scanner.nextLine());

        System.out.println("Do you want to:");
        System.out.println("1. Add a new location");
        System.out.println("2. Use an existing one ");
        int choice = scanner.nextInt();
        Location location = null;
        if (choice == 1) {
            System.out.println("What kind of location do you want to add? (1. Arena 2. Outdoor 3. Theatre");
            String choice_l = scanner.nextLine().toLowerCase();
            if (choice_l == "arena") {
                location = (Arena) addLocation(choice_l);
            }
            else if (choice_l == "outdoor") {
                location = (Outdoor) addLocation(choice_l);
            }
            else {
                location = (Theatre) addLocation(choice_l);
            }
        }
        else if (choice == 2) {
            System.out.println("List of the existing locations ascending by price per hour");
            //list locations
        }
        else {
            //Eroare try catch
            System.out.println("Invalid choice!");
        }

        //this.organizer = eu
        Organizer organizer = new Organizer();

        System.out.println("What is the standard price of the event ticket?");
        Integer ticket_price = scanner.nextInt();

        Play p = new Play(name, theme, act, date, start_time, end_time, location, organizer, ticket_price);
        return p;
    }

    public Conference addConference() {
        Scanner scanner = new Scanner(System.in);
        Conference c = new Conference();

        System.out.println("Name of the conference: ");
        c.setName(scanner.nextLine());

        System.out.println("Who is the host of the conference? ");
        c.setHost(scanner.nextLine());

        System.out.println("What is the theme of the conference? ");
        c.setTheme(scanner.nextLine());

        System.out.println("Date of the event: ");
        c.setDate(scanner.nextLine());

        System.out.println("Start time (hh:mm): ");
        c.setStart_time(Service.stringToHour(scanner.nextLine()));

        System.out.println("End time (hh:mm): ");
        c.setEnd_time(Service.stringToHour(scanner.nextLine()));

        System.out.println("Do you want to:");
        System.out.println("1. Add a new location");
        System.out.println("2. Use an existing one ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("What kind of location do you want to add? (1. Arena 2. Outdoor 3. Theatre");
            String choice_l = scanner.nextLine().toLowerCase();
            addLocation(choice_l); // Generic method for creating a new location
        }
        else if (choice == 2) {
            System.out.println("List of the existing locations ascending by price per hour");
            //list locations
        }
        else {
            //Eroare try catch
            System.out.println("Invalid choice!");
        }

        //this.organizer = eu

        System.out.println("What is the standard price of the event ticket?");
        c.setTicket_price(scanner.nextInt());

        return c;

    }
    //add client, child, organizer
    //add list events si order events
    //meniu
    //log in
    //add ticket
    //comparator sau comparable (eroare in event)
    //DE MODIF METODELE ADD SA APELEZE CONSTR CU PARAM -----done
}
