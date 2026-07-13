package com.phishingdetector;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        System.out.println("******** NEW MAIN RUNNING ********");
         EmailCSVReader reader = new EmailCSVReader();
         ArrayList<Email> emails = reader.readCSV("src/main/resources/emails.csv");
         NaiveBayesClassifier classifier = new NaiveBayesClassifier();
classifier.train(emails);
         DatasetGenerator generator = new DatasetGenerator();
generator.generate(emails);
        PhishingDetector detector = new PhishingDetector();
        int TP = 0;   // True Positive
int TN = 0;   // True Negative
int FP = 0;   // False Positive
int FN = 0;   // False Negative
        for (int i = 0; i < emails.size(); i++) {
        Email currentEmail = emails.get(i);
        FeatureExtractor extractor = new FeatureExtractor();
        System.out.println("===== RISK ANALYSIS =====");

RiskAnalyzer risk = new RiskAnalyzer();

int riskScore = risk.calculateRisk(currentEmail);

String riskLevel = risk.getRiskLevel(riskScore);

System.out.println("Risk Score : " + riskScore);
System.out.println("Risk Level : " + riskLevel);
        currentEmail.displayEmail();
        System.out.println("===== FEATURES =====");
        System.out.println("Risk Score    : " + riskScore);

System.out.println("Risk Level    : " + riskLevel);
System.out.println("Has URL        : " + extractor.hasURL(currentEmail));

System.out.println("URL Count      : " + extractor.urlCount(currentEmail));

System.out.println("Free Email     : " + extractor.isFreeEmail(currentEmail));


System.out.println("Subject Length : " + extractor.subjectLength(currentEmail));

System.out.println("Body Length    : " + extractor.bodyLength(currentEmail));

System.out.println("Keyword Count  : " + extractor.keywordCount(currentEmail));

System.out.println("====================");
        String prediction = classifier.predict(currentEmail);
        System.out.println("Prediction  : " + prediction);
        System.out.println("Actual Label: " + currentEmail.label);
       if (prediction.equals("1") && currentEmail.label.equals("1")) {
    TP++;
    System.out.println("✅ True Positive");
}
else if (prediction.equals("0") && currentEmail.label.equals("0")) {
    TN++;
    System.out.println("✅ True Negative");
}
else if (prediction.equals("1") && currentEmail.label.equals("0")) {
    FP++;
    System.out.println("❌ False Positive");
}
else {
    FN++;
    System.out.println("❌ False Negative");
}}
        System.out.println("\n========== RESULTS ==========");

System.out.println("True Positives  : " + TP);
System.out.println("True Negatives  : " + TN);
System.out.println("False Positives : " + FP);
System.out.println("False Negatives : " + FN);

int total = TP + TN + FP + FN;

double accuracy = ((TP + TN) * 100.0) / total;
double precision = (TP + FP) == 0 ? 0 : (TP * 100.0) / (TP + FP);

double recall = (TP + FN) == 0 ? 0 : (TP * 100.0) / (TP + FN);

double f1Score = (precision + recall) == 0 ? 0
        : (2 * precision * recall) / (precision + recall);

System.out.printf("Precision : %.2f%%\n", precision);
System.out.printf("Recall    : %.2f%%\n", recall);
System.out.printf("F1-Score  : %.2f%%\n", f1Score);

System.out.println("Accuracy : " + accuracy + "%");
    }
}