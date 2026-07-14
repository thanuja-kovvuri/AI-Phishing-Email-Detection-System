package com.phishingdetector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetectorGUI {

    public DetectorGUI() {

        JFrame frame = new JFrame("AI-Powered Phishing Email Detector");

        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        // Detect Button
JButton detectButton = new JButton("Detect Email");
detectButton.setBounds(250, 340, 180, 35);
panel.add(detectButton);
detectButton.addActionListener(e -> {

    Email email = new Email();

    email.sender = senderField.getText();
    email.subject = subjectField.getText();
    email.body = bodyArea.getText();

    FeatureExtractor extractor = new FeatureExtractor();

    RiskAnalyzer risk = new RiskAnalyzer();

    int score = risk.calculateRisk(email);

    String level = risk.getRiskLevel(score);

    String prediction = classifier.predict(email);

    if (prediction.equals("1"))
        predictionLabel.setText("Prediction : PHISHING");
    else
        predictionLabel.setText("Prediction : LEGITIMATE");

    riskScoreLabel.setText("Risk Score : " + score);

    riskLevelLabel.setText("Risk Level : " + level);

});

// Prediction Label
JLabel predictionLabel = new JLabel("Prediction : ");
predictionLabel.setBounds(30, 400, 300, 25);
panel.add(predictionLabel);

// Risk Score Label
JLabel riskScoreLabel = new JLabel("Risk Score : ");
riskScoreLabel.setBounds(30, 430, 300, 25);
panel.add(riskScoreLabel);

// Risk Level Label
JLabel riskLevelLabel = new JLabel("Risk Level : ");
riskLevelLabel.setBounds(30, 460, 300, 25);
panel.add(riskLevelLabel);
        panel.setLayout(null);

        // Sender
        JLabel senderLabel = new JLabel("Sender:");
        senderLabel.setBounds(30, 20, 100, 25);
        panel.add(senderLabel);

        JTextField senderField = new JTextField();
        senderField.setBounds(130, 20, 500, 25);
        panel.add(senderField);

        // Subject
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(30, 60, 100, 25);
        panel.add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(130, 60, 500, 25);
        panel.add(subjectField);

        // Body
        JLabel bodyLabel = new JLabel("Body:");
        bodyLabel.setBounds(30, 100, 100, 25);
        panel.add(bodyLabel);

        JTextArea bodyArea = new JTextArea();

        JScrollPane scroll = new JScrollPane(bodyArea);
        scroll.setBounds(130, 100, 500, 220);

        panel.add(scroll);

        frame.add(panel);
        NaiveBayesClassifier classifier = new NaiveBayesClassifier();

EmailCSVReader reader = new EmailCSVReader();

classifier.train(reader.readCSV("src/main/resources/emails.csv"));
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new DetectorGUI();

    }
}