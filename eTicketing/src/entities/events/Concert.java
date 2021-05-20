package entities.events;

import java.util.List;
import java.util.Objects;

import entities.tickets.TicketsBought;
import entities.locations.*;
import entities.person.Organizer;

public class Concert extends Event {
    private String name;
    private List<Singer> singers; // Composition

    public Concert() {} //Empty constructor

    public Concert(String name, String date, Hour start_time, Hour end_time, List<Singer> singers, Location location, Organizer organizer, float ticket_price) {
        super(date, start_time, end_time, organizer, location, ticket_price);
        this.name = name;
        this.singers = singers;
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

    public List<Singer> getSingers() { return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Name = " + name +"; Singers: " + singers + "; " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concert)) return false;
        if (!super.equals(o)) return false;
        Concert concert = (Concert) o;
        return Objects.equals(name, concert.name) && Objects.equals(singers, concert.singers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, singers);
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
        float duration = (float)no_of_hours + (float)no_of_minutes / 60.0F;
        return duration;
    }

    public float calcSingersPrice() {
        float sum = 0;
        for (int i = 0; i < this.singers.size(); i++) {
            sum += this.singers.get(i).getPrice_per_hour() * this.calcEventDuration();
        }
        return sum;
    }

    public float calcEventProfit() {
        Location e = null;
        float p = 0;
        if (this.location.getClass() == Arena.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcSingersPrice() - this.calcEventDuration() * ((Arena) e).getPrice_per_hour();
//            e = this.location;
//            e = (Arena)e;
//            p = this.calcMaxProfit() - this.calcSingersPrice() - this.calcEventDuration() * e.getPrice_per_hour();
        }
        if (this.location.getClass() == Outdoor.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcSingersPrice() - this.calcEventDuration() * ((Outdoor) e).getPrice_per_hour();
        }
        if (this.location.getClass() == Theatre.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcSingersPrice() - this.calcEventDuration() * ((Theatre) e).getPrice_per_hour();
        }
        return p;
    }

}
