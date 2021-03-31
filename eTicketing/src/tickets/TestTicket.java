package tickets;

import events.Concert;
import events.Hour;
import events.Singer;
import events.SingerType;
import locations.Arena;
import locations.Phone;
import person.Adult;
import person.Child;
import person.Organizer;
import person.Client;

import java.util.ArrayList;
import java.util.List;

public class TestTicket {
    public static void main(String[] args) {
        // Class for testing project features
        TicketsBought tickets = TicketsBought.getInstance();
        Phone phone1 = new Phone("0756548535");
        Client adult1 = new Adult("Ana", "Popescu", phone1);
        Singer singer1 = new Singer("Delia", 1000, SingerType.POP);
        Singer singer2 = new Singer("Andra", 1200, SingerType.POP);
        List<Singer> list1 = new ArrayList<Singer>();
        list1.add(singer1);
        list1.add(singer2);
        Hour hour1 = new Hour(18,0);
        Hour hour2 = new Hour(20, 30);
        Arena arena1 = new Arena("Arenele Romane", "Strada Tudor Vladimirescu, nr 2", phone1, 2500, 5000, 20000 );
        Organizer organizer1 = new Organizer("Popa", "Andreea", phone1);
        Concert concert1 = new Concert("Psihedelia", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        Concert concert2 = new Concert("N&D", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        Child copil1 = new Child("Alex", "Popescu");
        Ticket ticket1 = new Ticket(1, adult1, concert1, 1, 100);
        Ticket ticket2 = new Ticket(2, copil1, concert1, 2, 150);
        Ticket ticket3 = new Ticket(3, adult1, concert2, 3, 100);
        Ticket ticket4 = new Ticket(4, adult1, concert2, 4, 100);
        System.out.println(ticket1.toString());
        System.out.println(ticket2.calcPrice());  // 50
        System.out.println(ticket2.calcPrice()); // still 50: The price cannot be reduced multiple times.
        System.out.println(tickets.toString());
    }
}
