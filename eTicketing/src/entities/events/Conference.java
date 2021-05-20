package entities.events;

import entities.locations.Arena;
import entities.locations.Location;
import entities.locations.Outdoor;
import entities.locations.Theatre;
import entities.person.Organizer;
import entities.tickets.TicketsBought;

import java.util.Objects;

public class Conference extends Event {
    private String name;
    private String host;
    private String theme;

    public Conference() {} //Empty constructor

    public Conference(String name, String host, String theme, String date, Hour start_time, Hour end_time, Location location, Organizer organizer, float ticket_price) {
        super(date, start_time, end_time, organizer, location, ticket_price);
        this.name = name;
        this.host = host;
        this.theme = theme;
        if (TicketsBought.events != null) {
            if (!TicketsBought.events.containsKey(this)) {
                TicketsBought.events.put(this, 0);  //update the hashmap from TicketsBought
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Name = " + name + "; Host = " + host + "; Theme = " + theme + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return Objects.equals(name, that.name) && Objects.equals(host, that.host) && Objects.equals(theme, that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, host, theme);
    }

    @Override
    public float calcMaxProfit() {
        float v = this.getLocation().getCapacity() * this.getTicket_price();
        return v;
    }

    @Override
    protected float calcEventDuration() {
        int no_of_hours = this.end_time.getHour() - this.start_time.getHour();
        int no_of_minutes = this.end_time.getMinutes() - this.start_time.getMinutes();
        if (no_of_minutes < 0) {
            no_of_hours --;
            no_of_hours += 60;
        }
        float duration = no_of_hours + no_of_minutes / 60;
        return duration;
    }

    public float calcEventProfit() {
        Location e = null;
        float p = 0;
        if (this.location.getClass() == Arena.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcEventDuration() * ((Arena) e).getPrice_per_hour();
        }
        if (this.location.getClass() == Outdoor.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcEventDuration() * ((Outdoor) e).getPrice_per_hour();
        }
        if (this.location.getClass() == Theatre.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcEventDuration() * ((Theatre) e).getPrice_per_hour();
        }
        return p;
    }

}
