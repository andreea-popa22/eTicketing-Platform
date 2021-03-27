package tickets;

import java.util.HashMap;

// Singleton class for keeping track of the number of tickets bought for every event
// For every instance of an Event (either concert, play or conference), the name of the event is added in the hashmap (this happens in the Event class constructor
// For every instance of a Ticket, the value of the corresponding event in the hashmap is incremented (this happens in the Ticket class constructor)
public class TicketsBought {
    private static TicketsBought single_instance = null;
    public static HashMap<String, Integer> events;

    private TicketsBought() {
        events = new HashMap<>();
    }

    public static TicketsBought getInstance() {
        if (single_instance == null){
            single_instance = new TicketsBought();
        }
        return single_instance;
    }

    @Override
    public String toString() {
        return TicketsBought.events.toString();
    }
}
