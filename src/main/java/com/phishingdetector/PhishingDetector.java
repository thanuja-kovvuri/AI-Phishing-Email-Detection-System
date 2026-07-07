package com.phishingdetector;

public class PhishingDetector {

    public int calculateRisk(Email email) {

        int riskScore = 0;

        String sender = email.sender.toLowerCase();
        String subject = email.subject.toLowerCase();
        String body = email.body.toLowerCase();

        // Subject keywords
        String[] subjectKeywords = {
                "urgent", "verify", "account", "security",
                "login", "click", "password", "bank",
                "payment", "invoice", "confirm", "update",
                "limited", "winner", "prize", "gift",
                "free", "offer"
        };

        for (String keyword : subjectKeywords) {
            if (subject.contains(keyword)) {
                riskScore += 10;
            }
        }

        // Body keywords
        String[] bodyKeywords = {
                "click here", "login", "verify", "password",
                "bank", "credit card", "payment", "security",
                "update", "account", "otp", "urgent",
                "limited time", "winner", "free",
                "gift", "confirm"
        };

        for (String keyword : bodyKeywords) {
            if (body.contains(keyword)) {
                riskScore += 8;
            }
        }

        // Sender
        if (sender.contains("@gmail.com")
                || sender.contains("@yahoo.com")
                || sender.contains("@hotmail.com")
                || sender.contains("@outlook.com")) {

            System.out.println("⚠ Free Email Sender");
            riskScore += 15;
        }

        // URL detection
        if (body.contains("http://"))
            riskScore += 15;

        if (body.contains("https://"))
            riskScore += 15;

        if (body.contains("www."))
            riskScore += 10;

        if (body.contains("bit.ly"))
            riskScore += 20;

        if (body.contains("tinyurl"))
            riskScore += 20;

        // Formatting
        if (subject.contains("!!!"))
            riskScore += 5;

        if (body.contains("!!!"))
            riskScore += 5;

        if (subject.equals(subject.toUpperCase()) && subject.length() > 5)
            riskScore += 10;

        // Limit score
        if (riskScore > 100) {
            riskScore = 100;
        }

        return riskScore;
    }
}