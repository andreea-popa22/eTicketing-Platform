package services;

import entities.events.Actor;
import entities.events.Singer;
import entities.events.SingerType;
import entities.locations.Arena;
import entities.locations.Outdoor;
import entities.locations.Phone;
import entities.locations.Theatre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReadFromCSV {
    public static <E> ArrayList<E> readFromCSV(String option, String path) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        String row;
        ArrayList<E> objects = new ArrayList<E>();
        csvReader.readLine(); // Skip the first line (the header)
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            switch (option.toLowerCase()) {
                case "singer" -> {
                    String name = data[0];
                    Integer price_per_hour = Integer.parseInt(data[1]);
                    SingerType music_type = SingerType.valueOf(data[2].toUpperCase());
                    Singer s = new Singer(name, price_per_hour, music_type);
                    objects.add((E) s);
                }
                case "actor" -> {
                    String name = data[0];
                    Integer price_per_play = Integer.parseInt(data[1]);
                    Actor s = new Actor(name, price_per_play);
                    objects.add((E) s);
                }
                case "arena" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Arena s = new Arena(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
                case "outdoor" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Outdoor s = new Outdoor(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
                case "theatre" -> {
                    String name = data[0];
                    String address = data[1];
                    Phone phone = new Phone(data[2]);
                    Integer capacity = Integer.parseInt(data[3]);
                    Integer price_per_hour = Integer.parseInt(data[4]);
                    Integer surface = Integer.parseInt(data[5]);
                    Theatre s = new Theatre(name, address, phone, capacity, price_per_hour, surface);
                    objects.add((E) s);
                }
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuditService.auditWrite(timestamp.toString(), option.toLowerCase(), "Read");
        csvReader.close();
        return objects;
    }
}
