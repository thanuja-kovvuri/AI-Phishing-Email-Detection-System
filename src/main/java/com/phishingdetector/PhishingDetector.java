package com.phishingdetector;
public class PhishingDetector {

    public int calculateRisk(Email email){
        int riskScore = 0;
        
        if (email.link.contains("http://")) {
            System.out.println("⚠ Warning: Insecure link found!");
            riskScore += 30;
        } 
        if (email.subject.toLowerCase().contains("urgent")) {
            System.out.println("⚠ Suspicious Subject");
            riskScore += 20;
        }
        if (email.body.toLowerCase().contains("verify")) {
            System.out.println("⚠ Verification Message Found");
            riskScore += 25;
        }
    if(email.body.toLowerCase().contains("password")){
        System.out.println("⚠ Password Request");
        riskScore += 25;
    }
    if(email.sender.contains("@gmail.com")){
        System.out.println("⚠ Free Email Sender");
        riskScore += 15;
    }
    if(email.body.contains("http")){
        riskScore += 10;
    }
        return riskScore;
    }

}