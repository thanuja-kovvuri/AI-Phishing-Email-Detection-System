package com.phishingdetector;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class EmailCSVReader{

    public ArrayList<Email> readCSV(String filePath) {

        ArrayList<Email> emails = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));

            // Skip header
            reader.readNext();

            String[] parts;

            while ((parts = reader.readNext()) != null) {

                if (parts.length < 7) {
                    System.out.println("Skipping invalid row...");
                    continue;
                }

                Email email = new Email(
                        parts[0], // sender
                        parts[3], // subject
                        parts[4], // body
                        parts[6]  // urls
                );

                emails.add(email);
            }

            reader.close();
            System.out.println("CSV Loaded Successfully!");

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        return emails;
    }
}