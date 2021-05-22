package entities.tickets;

import java.util.HashMap;
import entities.events.*;

// Singleton class for keeping track of the number of tickets bought for every event
// For every instance of an Event (either concert, play or conference), the name of the event is added in the hashmap (this happens in the Event class constructor
// For every instance of a Ticket, the value of the corresponding event in the hashmap is incremented (this happens in the Ticket class constructor)
public class TicketsBought {
    private static TicketsBought single_instance = null;
    public static HashMap<Event, Integer> events;

    private TicketsBought() {
        events = new HashMap<Event, Integer>();
    }

    public static TicketsBought getInstance() {
        if (single_instance == null){
            single_instance = new TicketsBought();
        }
        return single_instance;
    }

    public static void print() {
        for (HashMap.Entry<Event, Integer> entry : events.entrySet()) {
            Event key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getName() + ": " + value);
        }
    }
}
