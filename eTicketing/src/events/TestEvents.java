package events;

import java.util.*;
import locations.*;
import locations.Phone;
import person.Organizer;
import usermenu.Service;

public class TestEvents {
    public static void main(String[] args) {
        Service service = Service.getInstance();
        Service.all_singers = new ArrayList<>();
        Service.all_actors = new ArrayList<>();
        Service.all_locations = new ArrayList<>();
        Service.all_events = new ArrayList<>();
        Singer singer1 = new Singer("Delia", 1000, SingerType.POP);
        Singer singer2 = new Singer("Andra", 1200, SingerType.POP);
        List<Singer> list1 = new ArrayList<Singer>();
        list1.add(singer1);
        list1.add(singer2);
        Hour hour1 = new Hour(18,0);
        Hour hour2 = new Hour(20, 30);
        Phone phone1 = new Phone("0756545675");
        Arena arena1 = new Arena("Arenele Romane", "Strada Tudor Vladimirescu, nr 2", phone1, 2500, 5000, 20000 );
        Organizer organizer1 = new Organizer("Popa", "Andreea", phone1);
        Concert concert1 = new Concert("Psihedelia", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        //System.out.println(concert1.toString());
        //System.out.println(Singer.singers);
        //System.out.println(concert1.calcMaxProfit());
        //System.out.println(concert1.calcEventDuration());
        Actor actor1 = new Actor("Mihai Bendeac", 250);
        Actor actor2 = new Actor("Carmen Tanase", 300);
        List<Actor> list2 = new ArrayList();
        list2.add(actor1);
        list2.add(actor2);
        Theatre theatre1 = new Theatre("Colibri", "Strada Vasile Alecsandri, nr 5", phone1, 2500, 2500, 1000);
        Play play1 = new Play("Bani din cer", PlayType.COMEDY, list2, "2021-07-09", hour1, hour2, theatre1, organizer1, 80);
        //System.out.println(play1.calcActorsPrice());
        //System.out.println(concert1.calcSingersPrice());
        //System.out.println(concert1.calcEventProfit());
        //p = this.calcMaxProfit() - this.calcSingersPrice() - this.calcEventDuration() * ((Outdoor) e).getPrice_per_hour();
        // 375.000 - 5500 - 12.500
        //System.out.println(play1.calcEventProfit());
        Event a = new Concert("Psihedelia", "2021-05-07", hour1, hour2, list1, arena1, organizer1, 150.0F);
        //System.out.println(a.getClass());
    }
}
