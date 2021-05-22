package usermenu;

import entities.events.*;
import entities.locations.*;
import entities.person.*;
import entities.tickets.*;
import entities.person.Organizer;
import services.ActorService;
import services.EventService;
import services.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        TicketsBought tickets = TicketsBought.getInstance();
        Service service = Service.getInstance();
        Service.all_singers = new ArrayList<>();
        Service.all_actors = new ArrayList<>();
        Service.all_locations = new ArrayList<>();
        Service.all_events = new ArrayList<>();
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
//                                EventService.listEvents();
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
//                                EventService.listEvents();
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
//                        EventService.listEvents();
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
//                        ev = EventService.addConcert(org);
//                        System.out.println("Congratulations! You created this event:");
//                        System.out.println(ev.toString());
//                        System.out.println("Event profit: " + ((Concert) ev).calcMaxProfit()); //show the organizer the profit that he is going to earn from the current event
//                        break;
//                    }
//                    else if (choice == 2) {
//                        ev = EventService.addPlay(org);
//                        System.out.println("Congratulations! You created this event:");
//                        System.out.println(ev.toString());
//                        System.out.println("Event profit: " + ((Play) ev).calcMaxProfit()); //show the organizer the profit that he is going to earn from the current event
//                        break;
//                    }
//                    else if (choice == 3) {
//                        ev = EventService.addConference(org);
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
        ActorService.addActor();
    }
}
