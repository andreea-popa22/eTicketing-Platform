package events;

import person.Organizer;
import locations.Location;

import java.util.Objects;

public abstract class Event {
    protected String date;
    protected Hour start_time;
    protected Hour end_time;
    protected Organizer organizer;
    protected Location location;
    protected float ticket_price;

    public Event() {} // Empty constructor

    public Event(String date, Hour start_time, Hour end_time, Organizer organizer, Location location, float ticket_price) {
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.organizer = organizer;
        this.location = location;
        this.ticket_price = ticket_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Hour getStart_time() {
        return start_time;
    }

    public void setStart_time(Hour start_time) {
        this.start_time = start_time;
    }

    public Hour getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Hour end_time) {
        this.end_time = end_time;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(float ticket_price) {
        this.ticket_price = ticket_price;
    }

    @Override
    public String toString() {
        return "Date = " + date + "; Start time = " + start_time + "; End time = " + end_time + "; Organizer: " + organizer.toString() + "; Location: " + location.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Float.compare(event.ticket_price, ticket_price) == 0 && Objects.equals(date, event.date) && Objects.equals(start_time, event.start_time) && Objects.equals(end_time, event.end_time) && Objects.equals(organizer, event.organizer) && Objects.equals(location, event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start_time, end_time, organizer, location, ticket_price);
    }

    public abstract String getName();

    protected abstract float calcMaxProfit(); // Abstract method

    protected abstract float calcEventDuration(); // Abstract method

    protected abstract float calcEventProfit(); // Abstract method

}
