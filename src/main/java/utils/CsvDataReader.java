package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.ApplicationRecord;

public class CsvDataReader {
    private static final String CsvPath = "src/test/java/resources/TestData.csv";

    public static List<ApplicationRecord> getApplicationsForUser(String userEmail) {
        List<ApplicationRecord> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CsvPath))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equalsIgnoreCase(userEmail)) { // filter by email
                    list.add(new ApplicationRecord(
                            data[1], // status
                            data[2], // referenceNumber
                            data[3], // applicationNumber
                            data[4], // permitType
                            data[5], // address
                            data[6], // date
                            data[7], // owner
                            data[8] // action
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading CSV: " + e.getMessage());
        }
        return list;
    }
}
