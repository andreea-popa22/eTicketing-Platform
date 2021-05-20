package services;

import entities.events.*;
import entities.locations.Location;
import entities.person.Organizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventService {
    public static void listEvents() {
        for (int i = 0; i < Service.all_events.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + Service.all_events.get(i).toString());
        }
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

        ArrayList<Singer> objects = null;
        try {
            objects = ReadFromCSV.readFromCSV("singer", "./eTicketing/src/resources/singers.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objects.size(); i++) {
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
                sin.add(Service.all_singers.get(index)); //add the singer of the current index in the concert singers list
            }
        }
        System.out.println("How many new singers do you want to add?: ");
        int no_of_new_singers = scanner.nextInt();
        for (int i = 0; i < no_of_new_singers; i++) {
            sin.add(SingerService.addSinger());
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
                        event_location = LocationService.addLocation("arena");
                        try {
                            WriteToCSV.writeToCSV(event_location, "./eTicketing/src/resources/arenas.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (choice == 2) {
                        event_location = LocationService.addLocation("outdoor");
                        try {
                            WriteToCSV.writeToCSV(event_location, "./eTicketing/src/resources/outdoors.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (choice == 3) {
                        event_location = LocationService.addLocation("theatre");
                        try {
                            WriteToCSV.writeToCSV(event_location, "./eTicketing/src/resources/theatres.csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            } else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = LocationService.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size() || location_index <= 0) {
                        System.out.println("Invalid index!");
                    } else {
                        event_location = locs.get(location_index - 1);
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

        ArrayList<Actor> objects = null;
        try {
            objects = ReadFromCSV.readFromCSV("actor", "./eTicketing/src/resources/actors.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objects.size(); i++) {
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
                act.add(Service.all_actors.get(index)); //add the actor of the current index in the play actors list
            }
        }
        System.out.println("How many new actors do you want to add?: ");
        int no_of_new_actors = scanner.nextInt();
        for (int i = 0; i < no_of_new_actors; i++) {
            act.add(ActorService.addActor());
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
                        event_location = LocationService.addLocation("arena");
                        break;
                    } else if (choice == 2) {
                        event_location = LocationService.addLocation("outdoor");
                        break;
                    } else if (choice == 3) {
                        event_location = LocationService.addLocation("theatre");
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            } else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = LocationService.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size()) {
                        System.out.println("Invalid index!");
                    } else {
                        event_location = locs.get(location_index - 1);
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
                        event_location = LocationService.addLocation("arena");
                        break;
                    } else if (choice == 2) {
                        event_location = LocationService.addLocation("outdoor");
                        break;
                    } else if (choice == 3) {
                        event_location = LocationService.addLocation("theatre");
                        break;
                    } else {
                        System.out.println("Invalid choice! ");
                    }
                }
                break;
            } else if (choice == 2) {
                System.out.println("The list of locations ordered by capacity:");
                List<Location> locs = LocationService.listSortedLocations(Service.all_locations);
                System.out.println("Choose location (write the index)");
                int location_index = 0;
                while (true) {
                    location_index = scanner.nextInt();
                    if (location_index > locs.size()) {
                        System.out.println("Invalid index!");
                    } else {
                        event_location = locs.get(location_index - 1);
                        break;
                    }
                }
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("What is the standard price of the event ticket?");
        float ticket_price = scanner.nextInt();

        Conference c = new Conference(name, host, theme, date, start_time, end_time, event_location, org, ticket_price);
        return c;

    }
}
