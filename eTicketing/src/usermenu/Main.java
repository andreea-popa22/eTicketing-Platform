package usermenu;

import events.Singer;
import events.SingerType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service.all_singers = new ArrayList<>();
        Singer singer1 = new Singer("Delia", 1000, SingerType.POP);
        Singer singer2 = new Singer("Andra", 1200,  SingerType.POP);
        Service service = Service.getInstance();
        //System.out.println("What type of user are you? (1. Client 2. Organizer 3. Admin");
        //Service.addLocation("arena");
        //Service.listAllSingers();
        Service.sortSingersByName(Service.all_singers);
        Service.listAllSingers();
    }
}
