package events;

import java.util.List;
import java.util.Objects;

import locations.Arena;
import locations.Location;
import locations.Outdoor;
import locations.Theatre;
import person.Organizer;
import tickets.TicketsBought;

public class Play extends Event {
    private String name;
    private PlayType theme;
    private List<Actor> actors; //Composition

    public Play() {} // Empty constructor

    public Play(String name, PlayType theme, List<Actor> actors, String date, Hour start_time, Hour end_time, Location location, Organizer organizer, float ticket_price) {
        super(date, start_time, end_time, organizer, location, ticket_price);
        this.name = name;
        this.theme = theme;
        this.actors = actors;
        if (TicketsBought.events != null) {
            if (!TicketsBought.events.containsKey(this)) {
                TicketsBought.events.put(this, 0);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayType getTheme() {
        return theme;
    }

    public void setTheme(PlayType theme) {
        this.theme = theme;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Name = " + name + "; Theme = " + theme + "; Actors = " + actors + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Play)) return false;
        if (!super.equals(o)) return false;
        Play play = (Play) o;
        return Objects.equals(name, play.name) && theme == play.theme && Objects.equals(actors, play.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, theme, actors);
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

    public Integer calcActorsPrice() {
        Integer sum = 0;
        for (int i = 0; i < this.actors.size(); i++) {
            sum += this.actors.get(i).getPrice_per_play();
        }
        return sum;
    }

    public float calcEventProfit() {
        Location e = null;
        float p = 0;
        if (this.location.getClass() == Arena.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcActorsPrice() - this.calcEventDuration() * ((Arena) e).getPrice_per_hour();
        }
        if (this.location.getClass() == Outdoor.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcActorsPrice() - this.calcEventDuration() * ((Outdoor) e).getPrice_per_hour();
        }
        if (this.location.getClass() == Theatre.class) {
            e = this.location;
            p = this.calcMaxProfit() - this.calcActorsPrice() - this.calcEventDuration() * ((Theatre) e).getPrice_per_hour();
        }
        return p;
    }
}
