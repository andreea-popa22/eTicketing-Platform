package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuditService {
    public static void auditWrite(String ts, String object, String action) throws IOException {
        File file = new File("./src/resources/audit.csv");
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter("./src/resources/audit.csv", true);
                csvWriter.append("Action name,Timestamp\n");
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter("./src/resources/audit.csv", true);
            csvWriter.append(action + " " + object + "," + ts + "\n");
            csvWriter.close();
        }
    }
}
