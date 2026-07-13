package com.phishingdetector;

import java.io.FileWriter;
import java.util.ArrayList;

public class DatasetGenerator {

    public void generate(ArrayList<Email> emails) {

        FeatureExtractor extractor = new FeatureExtractor();

        try {

            FileWriter writer = new FileWriter("features.csv");

            writer.write("HasURL,URLCount,FreeEmail,SubjectLength,BodyLength,KeywordCount,Label\n");
            for (Email email : emails) {

                writer.write(
    extractor.hasURL(email) + "," +
    extractor.urlCount(email) + "," +
    extractor.isFreeEmail(email) + "," +
    extractor.subjectLength(email) + "," +
    extractor.bodyLength(email) + "," +
    extractor.keywordCount(email) + "," +
    email.label + "\n");
            }

            writer.close();

            System.out.println("✅ features.csv created successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}