package com.phishingdetector;
import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayesClassifier {

    private HashMap<String, Integer> phishingWords = new HashMap<>();
    private HashMap<String, Integer> legitimateWords = new HashMap<>();

    private int phishingCount = 0;
    private int legitimateCount = 0;

    private int totalPhishingWords = 0;
    private int totalLegitimateWords = 0;

    private int vocabularySize = 0;
    
    

    public void train(ArrayList<Email> emails) {

    System.out.println("Training Naive Bayes Model...");
        phishingWords.clear();
legitimateWords.clear();

phishingCount = 0;
legitimateCount = 0;

totalPhishingWords = 0;
totalLegitimateWords = 0;
vocabularySize = 0;

    for (Email email : emails) {

        String text = (email.subject + " " + email.body).toLowerCase();

        String[] words = text.split("\\W+");

        if (email.label.equals("1")) {

            phishingCount++;

            for (String word : words) {

                if (word.isEmpty()) continue;

                phishingWords.put(word,
        phishingWords.getOrDefault(word,0)+1);

totalPhishingWords++;

            }

        }

        else {

            legitimateCount++;

            for (String word : words) {

                if(word.isEmpty() || StopWords.words.contains(word))
    continue;

                legitimateWords.put(word,
        legitimateWords.getOrDefault(word,0)+1);

totalLegitimateWords++;

            }

        }

    }
    HashMap<String, Integer> vocabulary = new HashMap<>();

vocabulary.putAll(phishingWords);
vocabulary.putAll(legitimateWords);

vocabularySize = vocabulary.size();
System.out.println("Vocabulary Size : " + vocabularySize);

System.out.println("Total Phishing Words : " + totalPhishingWords);

System.out.println("Total Legitimate Words : " + totalLegitimateWords);

    System.out.println("Training Complete!");

    System.out.println("Phishing Emails : " + phishingCount);

    System.out.println("Legitimate Emails : " + legitimateCount);

    System.out.println("Unique Phishing Words : " + phishingWords.size());

    System.out.println("Unique Legitimate Words : " + legitimateWords.size());

}
public String predict(Email email) {

    String text = (email.subject + " " + email.body).toLowerCase();

    String[] words = text.split("\\W+");

    double phishingScore =
            Math.log((double) phishingCount /
                    (phishingCount + legitimateCount));

    double legitimateScore =
            Math.log((double) legitimateCount /
                    (phishingCount + legitimateCount));

    for (String word : words) {

        if(word.isEmpty() || StopWords.words.contains(word))
    continue;

        int phishingFrequency =
                phishingWords.getOrDefault(word,0);

        int legitimateFrequency =
                legitimateWords.getOrDefault(word,0);

        double phishingProbability =
                (double)(phishingFrequency + 1) /
                (totalPhishingWords + vocabularySize);

        double legitimateProbability =
                (double)(legitimateFrequency + 1) /
                (totalLegitimateWords + vocabularySize);

        phishingScore += Math.log(phishingProbability);

        legitimateScore += Math.log(legitimateProbability);
    }

    if(phishingScore > legitimateScore)
        return "1";
    else
        return "0";
}
}