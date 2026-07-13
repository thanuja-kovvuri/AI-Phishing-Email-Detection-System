
package com.phishingdetector;

public class RiskAnalyzer {

    public int calculateRisk(Email email) {

        FeatureExtractor extractor = new FeatureExtractor();

        int score = 0;
        if (extractor.hasURL(email) == 1) {

    score += 2;

    System.out.println("✔ URL detected (+2)");
}

       if (extractor.isFreeEmail(email) == 1) {

    score += 1;

    System.out.println("✔ Free email sender (+1)");
}
int keywords = extractor.keywordCount(email);

if (keywords > 0) {

    score += keywords;

    System.out.println("✔ Suspicious keywords (+" + keywords + ")");
}
System.out.println("--------------------------");
        return score;
    }

    public String getRiskLevel(int score) {

        if (score >= 6)
            return "HIGH";

        if (score >= 3)
            return "MEDIUM";

        return "LOW";
    }
}