package com.phishingdetector;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
         EmailCSVReader reader = new EmailCSVReader();
         ArrayList<Email> emails = reader.readCSV("phishingemaildetector/src/main/resources/emails.csv");
        PhishingDetector detector = new PhishingDetector();
        for (int i = 0; i < emails.size(); i++) {
        Email currentEmail = emails.get(i);
        currentEmail.displayEmail();
        int score = detector.calculateRisk(currentEmail);
        System.out.println("Risk Score : " + score);
        if (score >= 50) {
            System.out.println("🚨 PHISHING EMAIL");
        }
         else {
            System.out.println("✅ SAFE EMAIL");
        }
        System.out.println("---------------------");
    }
}}