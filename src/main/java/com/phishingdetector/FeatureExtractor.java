package com.phishingdetector;

public class FeatureExtractor {

    public int hasURL(Email email) {
        String body = email.body.toLowerCase();

        if (body.contains("http://")
                || body.contains("https://")
                || body.contains("www.")) {
            return 1;
        }
        return 0;
    }
    public int urlCount(Email email) {

    String body = email.body.toLowerCase();

    int count = 0;

    String[] words = body.split("\\s+");

    for (String word : words) {

        if (word.startsWith("http://")
                || word.startsWith("https://")
                || word.startsWith("www.")) {

            count++;
        }
    }

    return count;
}

    public int isFreeEmail(Email email) {
        String sender = email.sender.toLowerCase();

        if (sender.contains("@gmail.com")
                || sender.contains("@yahoo.com")
                || sender.contains("@hotmail.com")
                || sender.contains("@outlook.com")) {
            return 1;
        }

        return 0;
    }

    public int subjectLength(Email email) {
        return email.subject.length();
    }

    public int bodyLength(Email email) {
        return email.body.length();
    }

    public int keywordCount(Email email) {

        String body = email.body.toLowerCase();

        String[] keywords = {
                "verify",
                "password",
                "bank",
                "payment",
                "login",
                "account",
                "urgent",
                "click",
                "free",
                "winner"
        };

        int count = 0;

        for (String word : keywords) {
            if (body.contains(word)) {
                count++;
            }
        }

        return count;
    }
}