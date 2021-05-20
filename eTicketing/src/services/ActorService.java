package services;

import entities.events.Actor;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ActorService {
    public static Actor addActor() {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Actor's name: ");
        String name = scanner1.nextLine();

        System.out.println("Price per play: ");
        Integer price_per_play = scanner1.nextInt();

        Actor a = new Actor(name, price_per_play);
        try {
            WriteToCSV.writeToCSV(a, "./eTicketing/src/resources/actors.csv");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return a;
    }

    public static void listAllActors() {
        for (int i = 0; i < Actor.actors; i++) {
            int index = i + 1;
            System.out.println(index + ". " + Service.all_actors.get(i).getName()); //print the name of the singer
        }
    }

    public static List<Actor> sortActorsByName(List<Actor> l) {
        l.sort(Comparator.comparing(Actor::getName));
        return l;
    }
}
