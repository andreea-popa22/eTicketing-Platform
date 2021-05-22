package services;

import entities.events.Singer;
import entities.events.SingerType;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SingerService {
    public static Singer addSinger() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Singer's name: ");
        String name = scanner.nextLine();

        System.out.println("Price per hour: ");
        Integer price_per_hour = scanner.nextInt();

        System.out.println("Music type: ");
        String type = scanner.next();
        SingerType stype = SingerType.valueOf(type.toUpperCase());

        Singer s = new Singer(name, price_per_hour, stype);  //Calling parameterized constructor so that the list of singers would be updated
        try {
            WriteToCSV.writeToCSV(s, "./src/resources/singers.csv");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return s;
    }

    public static List<Singer> sortSingersByName(List<Singer> l) {
        l.sort(Comparator.comparing(Singer::getName));
        return l;
    }

    public static void listAllSingers() {
        for (int i = 0; i < Singer.singers; i++) {
            int index = i + 1;
            System.out.println(index + ". " + Service.all_singers.get(i).getName()); //print the name of the singer
        }
    }
}
