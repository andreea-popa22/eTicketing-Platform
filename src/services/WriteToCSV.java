package services;

import entities.events.Actor;
import entities.events.Singer;
import entities.locations.Arena;
import entities.locations.Outdoor;
import entities.locations.Theatre;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class WriteToCSV {
    public static <E> void writeToCSV(E object, String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter(path, true);
                switch (object.getClass().getSimpleName()) {
                    case "Singer" -> csvWriter.append("Name,Price per hour,Music Type\n");
                    case "Actor" -> csvWriter.append("Name,Price per play\n");
                    case "Arena", "Theatre", "Outdoor" -> csvWriter.append("Name,Address,Contact,Capacity,Price per hour,Surface\n");
                }
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter(path, true);
            switch (object.getClass().getSimpleName()) {
                case "Singer" -> {
                    Singer s = (Singer) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getMusic_type().toString());
                    csvWriter.append("\n");
                }
                case "Actor" -> {
                    Actor s = (Actor) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_play().toString());
                    csvWriter.append("\n");
                }
                case "Arena" -> {
                    Arena s = (Arena) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
                case "Outdoor" -> {
                    Outdoor s = (Outdoor) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
                case "Theatre" -> {
                    Theatre s = (Theatre) object;
                    csvWriter.append(s.getName());
                    csvWriter.append(",");
                    csvWriter.append(s.getAddress());
                    csvWriter.append(",");
                    csvWriter.append(s.getContact().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getCapacity().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getPrice_per_hour().toString());
                    csvWriter.append(",");
                    csvWriter.append(s.getSurface().toString());
                    csvWriter.append("\n");
                }
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            AuditService.auditWrite(timestamp.toString(), object.getClass().getSimpleName(), "Write");
            csvWriter.close();
        }
    }
}
