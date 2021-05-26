package usermenu;

import config.DatabaseConfiguration;
import entities.locations.*;
import entities.events.*;
import repositories.*;
import services.ActorService;
import services.LocationService;
import services.Service;
import services.SingerService;

import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseMenu {
    public static void main(String[] args) {
        DatabaseConfiguration.getDatabaseConnection();
        Service.initLists();

        ActorRepository act = new ActorRepository();
        SingerRepository sin = new SingerRepository();
        ArenaRepository arn = new ArenaRepository();
        OutdoorRepository odr = new OutdoorRepository();
        TheatreRepository thr = new TheatreRepository();

        System.out.println("Welcome to eTicketing!");
        System.out.println("What do you want to do?");
        System.out.println("1. Add new Singer");
        System.out.println("2. Add new Actor");
        System.out.println("3. Add new Arena");
        System.out.println("4. Add new Outdoor Location");
        System.out.println("5. Add new Theatre");
        System.out.println("-----------------------------------");
        System.out.println("6. Select Singer by id");
        System.out.println("7. Select Actor by id");
        System.out.println("8. Select Arena by id");
        System.out.println("9. Select Outdoor Location by id");
        System.out.println("10. Select Theatre by id");
        System.out.println("-----------------------------------");
        System.out.println("11. Modify Singer's data");
        System.out.println("12. Modify Actor's data");
        System.out.println("13. Modify Arena price");
        System.out.println("14. Modify Outdoor price");
        System.out.println("15. Modify Theatre price");
        System.out.println("-----------------------------------");
        System.out.println("16. Remove Singer");
        System.out.println("17. Remove Actor");
        System.out.println("18. Remove Arena");
        System.out.println("19. Remove Outdoor");
        System.out.println("20. Remove Theatre");
        System.out.println("-----------------------------------");
        System.out.println("21. Display all Singers");
        System.out.println("22. Display all Actors");
        System.out.println("23. Display all Arenas");
        System.out.println("24. Display all Outdoor Locations");
        System.out.println("25. Display all Theatre");
        System.out.println();
        System.out.println("Choose: ");

        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        if (option == 1){
            Singer singer = SingerService.addSinger();
            sin.insertSinger(singer);
        }
        else if (option == 2){
            Actor actor = ActorService.addActor();
            act.insertActor(actor);
        }
        else if (option == 3){
            Arena arena = (Arena)LocationService.addLocation("arena");
            arn.insertArena(arena);
        }
        else if (option == 4){
            Outdoor outdoor = (Outdoor)LocationService.addLocation("outdoor");
        }
        else if (option == 5){
            Theatre theatre = (Theatre)LocationService.addLocation("theatre");
        }
        else if (option == 6){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Singer s = sin.getSingerById(index);
            System.out.println(s.toString());
        }
        else if (option == 7){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Actor a = act.getActorById(index);
            System.out.println(a.toString());
        }
        else if (option == 8){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Arena a = arn.getArenaById(index);
            System.out.println(a.toString());
        }
        else if (option == 9){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Outdoor o = odr.getOutdoorById(index);
            System.out.println(o.toString());
        }
        else if (option == 10){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Theatre t = thr.getTheatreById(index);
            System.out.println(t.toString());
        }
        else if (option == 11){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Singer s = SingerService.addSinger();
            sin.updateSingerName(index, s.getName(), s.getPrice_per_hour(),  s.getMusic_type());
        }
        else if (option == 12){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            Actor a = ActorService.addActor();
            act.updateActorName(index, a.getName(), a.getPrice_per_play());
        }
        else if (option == 13){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            System.out.println("New price: ");
            int price = in.nextInt();
            arn.updateArenaPrice(index, price);
        }
        else if (option == 14){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            System.out.println("New price: ");
            int price = in.nextInt();
            odr.updateOutdoorPrice(index, price);
        }
        else if (option == 15){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            System.out.println("New price: ");
            int price = in.nextInt();
            thr.updateTheatrePrice(index, price);
        }
        else if (option == 16){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            sin.removeSinger(index);
        }
        else if (option == 17){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            act.removeActor(index);
        }
        else if (option == 18){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            arn.removeArena(index);
        }
        else if (option == 19){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            odr.removeOutdoor(index);
        }
        else if (option == 20){
            System.out.println("Write the id: ");
            int index = in.nextInt();
            thr.removeTheatre(index);
        }
        else if (option == 21){
            sin.displaySingers();
        }
        else if (option == 22){
            act.displayActors();
        }
        else if (option == 23){
            arn.displayArenas();
        }
        else if (option == 24){
            odr.displayOutdoors();
        }
        else if(option == 25){
            thr.displayTheatres();
        }
        else {
            System.out.println("Invalid choice!");
        }
    }
}
