package com.phishingdetector;
public class Email {
    String label;
    String sender;
    String subject;
    String body;
    String link;
    
    // Parameterized Constructor
    public Email(String sender, String subject, String body, String link,String label) {
        this.sender = sender;
        this.subject = subject;
        this.body = body;
        this.link = link;
        this.label=label;
    }

    // Method to display email details
    public void displayEmail() {
        System.out.println("Sender  : " + sender);
        System.out.println("Subject : " + subject);
        System.out.println("Body    : " + body);
        System.out.println("Link    : " + link);
    }
}