package usermenu;

import events.*;
import locations.*;
import person.*;
import tickets.*;
import person.Organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // The following objects where created to highlight the project functionalities (for the first stage)
        TicketsBought tickets = TicketsBought.getInstance();
        Service service = Service.getInstance();
        Service.all_singers = new ArrayList<>();
        Service.all_actors = new ArrayList<>();
        Service.all_locations = new ArrayList<>();
        Service.all_events = new ArrayList<>();
        Singer singer1 = new Singer("Delia", 1000, SingerType.POP);
        Singer singer2 = new Singer("Andra", 1200, SingerType.POP);
        Singer singer3 = new Singer("Smiley", 950, SingerType.HIPHOP);
        List<Singer> list1 = new ArrayList<Singer>();
        list1.add(singer1);
        list1.add(singer2);
        list1.add(singer3);
        Actor actor1 = new Actor("Mihai Bendeac", 400);
        Actor actor2 = new Actor("Carmen Tanase", 450);
        Actor actor3 = new Actor("Horatiu Malaele", 600);
        List<Actor> list2 = new ArrayList();
        list2.add(actor1);
        list2.add(actor2);
        list2.add(actor3);
        Hour hour1 = new Hour(18, 0);
        Hour hour2 = new Hour(20, 30);
        Phone phone1 = new Phone("0756545675");
        Phone phone2 = new Phone("0756542575");
        Arena arena1 = new Arena("Arenele Romane", "Strada Tudor Vladimirescu, nr 2", phone1, 2500, 5000, 20000);
        Theatre theatre1 = new Theatre("Colibri", "Strada Vasile Alecsandri, nr 5", phone1, 2500, 2500, 1000);
        Outdoor outdoor1 = new Outdoor("Piata Sfatului", "Strada Ion Minculescu 13", phone2, 5000, 1000, 10000);
        Organizer organizer1 = new Organizer("Popa", "Andreea", phone2);
        Concert concert1 = new Concert("Psihedelia", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        Play play1 = new Play("Bani din cer", PlayType.COMEDY, list2, "2021-07-09", hour1, hour2, theatre1, organizer1, 80);
        Conference conference1 = new Conference("Time Management", "Cristian Dascalu", "Self Development", "2021-05-22", hour1, hour2, theatre1, organizer1, 100.0F);
        Adult adult1 = new Adult("Alexandru", "Neagu", phone2);
        Child child1 = new Child("Stefan Vlad", "Neagu");
        Concert concert2 = new Concert("N&D", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        Child copil1 = new Child("Alex", "Popescu");
        Ticket ticket1 = new Ticket(1, adult1, concert1, 1, 100);
        Ticket ticket2 = new Ticket(2, child1, concert1, 2, 150);
        Ticket ticket3 = new Ticket(3, adult1, concert2, 3, 100);
        Ticket ticket4 = new Ticket(4, adult1, concert2, 4, 100);
        System.out.println("Welcome to eTicketing! What type of user are you? 1.Client 2.Organizer");
        int ticket_id = 4;
        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            int choice = scanner.nextInt();
//            if (choice == 1) {
//                System.out.println("Do you want to: 1. Buy a ticket 2. See available events");
//                while (true) {
//                    choice = scanner.nextInt();
//                    if (choice == 1){
//                        System.out.println("1.Adult ticket 2.Child ticket");
//                        while (true) {
//                            choice = scanner.nextInt();
//                            if (choice == 1) {
//                                Adult ad = (Adult)Service.addClient("adult");
//                                System.out.println("These are the available events:");
//                                Service.listEvents();
//                                System.out.println("Choose event (write the index)");
//                                int event_index = 0;
//                                while(true) {
//                                    event_index = scanner.nextInt();
//                                    if(event_index > Service.all_events.size()) {
//                                        System.out.println("Invalid index!");
//                                    }
//                                    else {
//                                        break;
//                                    }
//                                }
//                                ticket_id++;
//                                Event ticket_event = Service.all_events.get(event_index-1);
//                                int ticket_seat = TicketsBought.events.get(ticket_event) + 1;
//                                float ticket_price = ticket_event.getTicket_price();
//                                Ticket ticket = new Ticket(ticket_id, ad, ticket_event, ticket_seat, ticket_price); //Create new ticket so that TicketsBought is up to date
//                                System.out.println("You have to pay: " + ticket.getPrice() + " dollars");
//                                break;
//                            }
//                            else if (choice == 2) {
//                                Child ch = (Child)Service.addClient("child");
//                                System.out.println("These are the available events:");
//                                Service.listEvents();
//                                System.out.println("Choose event (write the index)");
//                                int event_index = 0;
//                                while(true) {
//                                    event_index = scanner.nextInt();
//                                    if(event_index > Service.all_events.size()) {
//                                        System.out.println("Invalid index!");
//                                    }
//                                    else {
//                                        break;
//                                    }
//                                }
//                                ticket_id++;
//                                Event ticket_event = Service.all_events.get(event_index-1); //assign the chosen event to the ticket instance event
//                                int ticket_seat = TicketsBought.events.get(ticket_event) + 1;
//                                float ticket_price = ticket_event.getTicket_price();
//                                Ticket ticket = new Ticket(ticket_id, ch, ticket_event, ticket_seat, ticket_price); //Create new ticket so that TicketsBought is up to date
//                                System.out.println("You have to pay: " + ticket.getPrice() + " dollars (discount applied)"); //the price for child tickets is automatically calculated with the discount
//                                break;
//                            }
//                            else {
//                                System.out.println("Invalid choice! ");
//                            }
//                        }
//                        break;
//                    }
//                    else if (choice == 2) {
//                        Service.listEvents();
//                        break;
//                    }
//                    else {
//                        System.out.println("Invalid choice! ");
//                    }
//                }
//                break;
//            }
//            else if (choice == 2) {
//                Organizer org = Service.addOrganizer();
//                System.out.println("1. Add concert 2. Add play 3. Add conference 4.See tickets bought");
//                Event ev = null;
//                while (true) {
//                    choice = scanner.nextInt();
//                    if (choice == 1){
//                        ev = Service.addConcert(org);
//                        System.out.println("Congratulations! You created this event:");
//                        System.out.println(ev.toString());
//                        System.out.println("Event profit: " + ((Concert) ev).calcMaxProfit()); //show the organizer the profit that he is going to earn from the current event
//                        break;
//                    }
//                    else if (choice == 2) {
//                        ev = Service.addPlay(org);
//                        System.out.println("Congratulations! You created this event:");
//                        System.out.println(ev.toString());
//                        System.out.println("Event profit: " + ((Play) ev).calcMaxProfit()); //show the organizer the profit that he is going to earn from the current event
//                        break;
//                    }
//                    else if (choice == 3) {
//                        ev = Service.addConference(org);
//                        System.out.println("Congratulations! You created this event:");
//                        System.out.println(ev.toString());
//                        System.out.println("Event profit: " + ((Conference) ev).calcEventProfit()); //show the organizer the profit that he is going to earn from the current event
//                        break;
//                    }
//                    else if (choice == 4){
//                        TicketsBought.print();
//                        break;
//                    }
//                    else {
//                        System.out.println("Invalid choice! ");
//                    }
//                }
//                break;
//            }
//            else {
//                System.out.println("Invalid choice! ");
//            }
//        }
//    }
        //Singer ss = Service.addSinger();
        //Service.addActor();
        //Service.addLocation("outdoor");
        Service.addConcert(organizer1);
    }
}
