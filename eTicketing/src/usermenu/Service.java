package usermenu;

import locations.*;
import person.*;
import events.*;

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

    private Service() {} ;

    public static Service getInstance() {
        if (single_instance == null){
            single_instance = new Service();
        }
        return single_instance;
    }

    // Method for manipulating a String into an Hour class object
    public static Hour stringToHour(String s){
        Integer hour = Integer.parseInt(s.substring(0,2));
        Integer minutes = Integer.parseInt(s.substring(3,5));
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
        String type = scanner.next();
        SingerType stype = SingerType.valueOf(type.toUpperCase());

        Singer s = new Singer(name, price_per_hour, stype);  //Calling parameterized constructor so that the list of singers would be updated
        try {
            writeToCSV(s, "src/singers.csv");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return s;
    }

    public static Actor addActor() {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Actor's name: ");
        String name = scanner1.nextLine();

        System.out.println("Price per play: ");
        Integer price_per_play = scanner1.nextInt();

        Actor a = new Actor(name, price_per_play);
        try {
            writeToCSV(a, "src/actors.csv");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return a;
    }

    public static Location addLocation(String location) {
        Scanner scanner = new Scanner(System.in);
        Location e = null;

        System.out.println("Location name: ");
        String name = scanner.nextLine();

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
                e = new Arena(name, address, contact, capacity, price_per_hour, surface);
                try {
                    writeToCSV(e, "src/arenas.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "outdoor" -> {
                e = new Outdoor(name, address, contact, capacity, price_per_hour, surface);
                try {
                    writeToCSV(e, "src/outdoors.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "theatre" -> {
                e = new Theatre(name, address, contact, capacity, price_per_hour, surface);
                try {
                    writeToCSV(e, "src/theatres.csv");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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

    public static List<Location> listSortedLocations(List<Location> l) {
        l.sort(Location::compareTo);
        for (int i = 0; i < Service.all_locations.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + Service.all_locations.get(i).getName());
        }
        return l;
    }

    public static Concert addConcert(Organizer org) {
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
        //all_singers = sortSingersByName(all_singers);
        //listAllSingers();

        ArrayList<Singer> objects = null;
        try {
            objects = readFromCSV("singer", "src/singers.csv" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objects.size(); i++){
            int index = i + 1;
            System.out.println(index + ". " + objects.get(i).toString());
        }

        List sin = new ArrayList();
        System.out.println("Which singers from this list do you want to add? (ex: 1 3 7 23 / you can leave it blank): ");
        String indexes = scanner.nextLine();
        if (indexes != "") {
            String[] ind = indexes.split(" ");
            for (int i = 0; i < ind.length; i++) {
                int index = Integer.parseInt(ind[i]) - 1;
                sin.add(all_singers.get(index)); //add the singer of the current index in the concert singers list
            }
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
        Location event_location = null;
        while (true) {
            if (choice == 1) {
                System.out.println("What kind of location do you want to add? 1.Arena 2.Outdoor 3.Theatre");
                while (true) {
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        event_location = Service.addLocation("arena");
                        try {
                            writeToCSV(event_location, "src/arenas.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (choice == 2) {
                        event_location = Service.addLocation("outdoor");
                        try {
                            writeToCSV(event_location, "src/outdoors.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (choice == 3) {
                        event_location = Service.addLocation("theatre");
                        try {
                            writeToCSV(event_location, "src/theatres.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            }
            else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = Service.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size() || location_index <= 0) {
                        System.out.println("Invalid index!");
                    } else {
                        event_location = locs.get(location_index-1);
                        break;
                    }
                }
                break;
            }
            else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("What is the standard price of the event ticket?");
        Integer ticket_price = scanner.nextInt();

        Concert c = new Concert(name, date, start_time, end_time, sin, event_location, org, ticket_price);
        return c;

    }

    public static Play addPlay(Organizer org) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the Play: ");
        String name = scanner.nextLine();

        System.out.println("Theme of the play (COMEDY / TRAGEDY / HISTORICAL / MUSICAL)");
        String t = scanner.nextLine();
        PlayType theme = PlayType.valueOf(t.toUpperCase());

        System.out.println("Date of the event: ");
        String date = scanner.nextLine();

        System.out.println("Start time (hh:mm): ");
        Hour start_time = Service.stringToHour(scanner.nextLine());

        System.out.println("End time (hh:mm): ");
        Hour end_time = Service.stringToHour(scanner.nextLine());

        System.out.println("These are the existing actors in alphabetical order: ");
        //all_actors = sortActorsByName(all_actors);
        //listAllActors();

        ArrayList<Actor> objects = null;
        try {
            objects = readFromCSV("actor", "src/actors.csv" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objects.size(); i++){
            int index = i + 1;
            System.out.println(index + ". " + objects.get(i).toString());
        }

        List act = new ArrayList();
        System.out.println("Which actors from this list do you want to add? (ex: 1 3 7 23 / you can leave it blank): ");
        String indexes = scanner.nextLine();
        if (indexes != "") {
            String[] ind = indexes.split(" ");
            for (int i = 0; i < ind.length; i++) {
                int index = Integer.parseInt(ind[i]) - 1;
                act.add(all_actors.get(index)); //add the actor of the current index in the play actors list
            }
        }
        System.out.println("How many new actors do you want to add?: ");
        int no_of_new_actors = scanner.nextInt();
        for (int i = 0; i < no_of_new_actors; i++){
            act.add(addActor());
        }

        System.out.println("Do you want to:");
        System.out.println("1. Add a new location");
        System.out.println("2. Use an existing one ");
        Location event_location = null;
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("What kind of location do you want to add? 1.Arena 2.Outdoor 3.Theatre");
                while (true) {
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        event_location = Service.addLocation("arena");
                        break;
                    } else if (choice == 2) {
                        event_location = Service.addLocation("outdoor");
                        break;
                    } else if (choice == 3) {
                        event_location = Service.addLocation("theatre");
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            } else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = Service.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size()) {
                        System.out.println("Invalid index!");
                    } else {
                        event_location = locs.get(location_index-1);
                        break;
                    }
                }
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("What is the standard price of the event ticket?");
        Integer ticket_price = scanner.nextInt();

        Play p = new Play(name, theme, act, date, start_time, end_time, event_location, org, ticket_price);
        return p;
    }

    public static Conference addConference(Organizer org) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the conference: ");
        String name = scanner.nextLine();

        System.out.println("Who is the host of the conference? ");
        String host = scanner.nextLine();

        System.out.println("What is the theme of the conference? ");
        String theme = scanner.nextLine();

        System.out.println("Date of the event: ");
        String date = scanner.nextLine();

        System.out.println("Start time (hh:mm): ");
        Hour start_time = Service.stringToHour(scanner.nextLine());

        System.out.println("End time (hh:mm): ");
        Hour end_time = Service.stringToHour(scanner.nextLine());

        System.out.println("Do you want to:");
        System.out.println("1. Add a new location");
        System.out.println("2. Use an existing one ");
        Location event_location = null;
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("What kind of location do you want to add? 1.Arena 2.Outdoor 3.Theatre");
                while (true) {
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        event_location = Service.addLocation("arena");
                        break;
                    } else if (choice == 2) {
                        event_location = Service.addLocation("outdoor");
                        break;
                    } else if (choice == 3) {
                        event_location = Service.addLocation("theatre");
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            }
            else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = Service.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size()) {
                        System.out.println("Invalid index!");
                    }
                    else {
                        event_location = locs.get(location_index-1);
                        break;
                    }
                }
                break;
            }
            else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("What is the standard price of the event ticket?");
        float ticket_price = scanner.nextInt();

        Conference c = new Conference(name, host, theme, date, start_time, end_time, event_location, org, ticket_price);
        return c;

    }

    public static Client addClient(String client) {
        Scanner scanner = new Scanner(System.in);
        Client c = null;

        System.out.println("Enter the details of the ticket holder");

        System.out.println("First name: ");
        String f_name  = scanner.nextLine();

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
                c = new Child(f_name,l_name);
                break;
            }
        }
        return c;
    }

    public static Organizer addOrganizer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name: ");
        String f_name  = scanner.nextLine();

        System.out.println("Last name: ");
        String l_name = scanner.nextLine();

        System.out.println("Phone number: ");
        Phone phone = addPhone();
        Organizer o = new Organizer(f_name, l_name, phone);

        return o;
    }

    public static void listEvents() {
        for (int i = 0; i < Service.all_events.size(); i++) {
            int index = i+1;
            System.out.println(index + ". " + Service.all_events.get(i).toString());
        }
    }

    public static <E> ArrayList<E> readFromCSV(String option, String path) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        String row;
        ArrayList<E> objects = new ArrayList<E>();
        csvReader.readLine(); // Skip the first line (the header)
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            switch (option.toLowerCase()) {
                case "singer" -> {
                    String name = data[0];
                    Integer price_per_hour = Integer.parseInt(data[1]);
                    SingerType music_type = SingerType.valueOf(data[2].toUpperCase());
                    Singer s = new Singer(name, price_per_hour,music_type);
                    objects.add((E) s);
                }
                case "actor" -> {
                    String name = data[0];
                    Integer price_per_play = Integer.parseInt(data[1]);
                    Actor s = new Actor(name, price_per_play);
                    objects.add((E) s);
                }
                case "arena" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Arena s = new Arena(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
                case "outdoor" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Outdoor s = new Outdoor(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
                case "theatre" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Theatre s = new Theatre(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        auditWrite(timestamp.toString(), option.toLowerCase(), "Read");
        csvReader.close();
        return objects;
    }

    public static <E> void writeToCSV(E object, String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter(path, true);
                switch (object.getClass().getSimpleName()) {
                    case "Singer" -> csvWriter.append("Name,Price per hour,Music Type\n");
                    case "Actor" -> csvWriter.append("Name,Price per play\n");
                    case "Arena", "Theatre", "Outdoor" -> csvWriter.append("Name,Address,Contact,Capacity,Price per hour,Surface\n");
                }
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter(path, true);
            switch (object.getClass().getSimpleName()) {
                case "Singer" -> {
                    Singer s = (Singer) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getMusic_type().toString());
                    csvWriter.append("\n");
                }
                case "Actor" -> {
                    Actor s = (Actor) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_play().toString());
                    csvWriter.append("\n");
                }
                case "Arena" -> {
                    Arena s = (Arena) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
                case "Outdoor" -> {
                    Outdoor s = (Outdoor) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
                case "Theatre" -> {
                    Theatre s = (Theatre) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            auditWrite(timestamp.toString(), object.getClass().getSimpleName(), "Write");
            csvWriter.close();
        }
    }

    public static void auditWrite(String ts, String object, String action) throws IOException{
        File file = new File("src/audit.csv");
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter("src/audit.csv", true);
                csvWriter.append("Action name,Timestamp\n");
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter("src/audit.csv", true);
            csvWriter.append(action + " " + object + "," + ts + "\n");
            csvWriter.close();
        }
    }
}
