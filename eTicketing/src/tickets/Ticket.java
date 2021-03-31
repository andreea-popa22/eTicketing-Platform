package tickets;

import person.Client;
import person.Adult;
import person.Child;
import events.*;

import java.util.Objects;

public class Ticket {
    private Integer id;
    private Client client;
    private Event event;
    private Integer seat;
    private float price;
    private boolean reduced = false;

    public Ticket() {}; // Empty constructor


    public Ticket(Integer id, Client client, Event event, Integer seat, float price) {
        this.id = id;
        this.client = client;
        this.event = event;
        this.seat = seat;
        this.price = price;
        calcPrice();  // calculate the price of the ticket depending oh the type of the client
        TicketsBought.events.put(event, TicketsBought.events.get(event) + 1); // Increment the number of tickets for the current event in the hashmap
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isReduced() {
        return reduced;
    }

    public void setReduced(boolean reduced) {
        this.reduced = reduced;
    }

    @Override
    public String toString() {
        return "Ticket ID = " + id + "; Client: " + client.toString() + "; Event: " + event.toString() + "; Seat = " + seat + "; Price = " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Float.compare(ticket.price, price) == 0 && reduced == ticket.reduced && id.equals(ticket.id) && client.equals(ticket.client) && event.equals(ticket.event) && seat.equals(ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, event, seat, price, reduced);
    }

    public float calcPrice() {
        if (this.client.getClass() == Adult.class) {
            this.setReduced(true);
            return this.getPrice();
        }
        else {
            if (this.client.getClass() == Child.class) {
                Child c = null;
                c = (Child)this.client;
                if (!this.isReduced()) {
                    this.setPrice(this.getPrice() * c.discount);
                    this.setReduced(true);
                    return this.getPrice();
                }
                else {
                    return this.getPrice();
                }
            }
        }
        return 0;
    }
}
