package com.phishingdetector;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DetectorGUI {

    private JTextField senderField;
    private JTextField subjectField;
    private JTextArea bodyArea;

    private JLabel predictionLabel;
    private JLabel riskScoreLabel;
    private JLabel riskLevelLabel;

    private NaiveBayesClassifier classifier;

    public DetectorGUI() {

        // ---------------- TRAIN MODEL ----------------
        classifier = new NaiveBayesClassifier();

        EmailCSVReader reader = new EmailCSVReader();
        ArrayList<Email> emails =
                reader.readCSV("src/main/resources/emails.csv");

        classifier.train(emails);

        // ---------------- FRAME ----------------
        JFrame frame = new JFrame("AI-Powered Phishing Email Detector");

        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // ---------------- Sender ----------------
        JLabel senderLabel = new JLabel("Sender:");
        senderLabel.setBounds(30, 20, 100, 25);
        panel.add(senderLabel);

        senderField = new JTextField();
        senderField.setBounds(130, 20, 500, 25);
        panel.add(senderField);

        // ---------------- Subject ----------------
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(30, 60, 100, 25);
        panel.add(subjectLabel);

        subjectField = new JTextField();
        subjectField.setBounds(130, 60, 500, 25);
        panel.add(subjectField);

        // ---------------- Body ----------------
        JLabel bodyLabel = new JLabel("Body:");
        bodyLabel.setBounds(30, 100, 100, 25);
        panel.add(bodyLabel);

        bodyArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setBounds(130, 100, 500, 220);
        panel.add(scrollPane);

        // ---------------- Detect Button ----------------
        JButton detectButton = new JButton("Detect Email");
        detectButton.setBounds(240, 340, 180, 35);
        panel.add(detectButton);

        // ---------------- Result Labels ----------------
        predictionLabel = new JLabel("Prediction : ");
        predictionLabel.setBounds(30, 400, 500, 25);
        panel.add(predictionLabel);

        riskScoreLabel = new JLabel("Risk Score : ");
        riskScoreLabel.setBounds(30, 430, 500, 25);
        panel.add(riskScoreLabel);

        riskLevelLabel = new JLabel("Risk Level : ");
        riskLevelLabel.setBounds(30, 460, 500, 25);
        panel.add(riskLevelLabel);

        // ---------------- Button Action ----------------
        detectButton.addActionListener(e -> {

            Email email = new Email(
                    senderField.getText(),
                    subjectField.getText(),
                    bodyArea.getText(),
                    "",
                    "0"
            );

            FeatureExtractor extractor = new FeatureExtractor();
            RiskAnalyzer riskAnalyzer = new RiskAnalyzer();

            int score = riskAnalyzer.calculateRisk(email);
            String level = riskAnalyzer.getRiskLevel(score);
            String prediction = classifier.predict(email);

if (prediction.equals("1") || score >= 6) {

    predictionLabel.setText("Prediction : PHISHING");
    predictionLabel.setForeground(Color.RED);

}
else if (score >= 3) {

    predictionLabel.setText("Prediction : SUSPICIOUS");
    predictionLabel.setForeground(Color.ORANGE);

}
else {

    predictionLabel.setText("Prediction : LEGITIMATE");
    predictionLabel.setForeground(new Color(0, 128, 0));

}
            riskScoreLabel.setText("Risk Score : " + score);
            riskLevelLabel.setText("Risk Level : " + level);

            System.out.println("===== FEATURES =====");
            System.out.println("Has URL : " + extractor.hasURL(email));
            System.out.println("URL Count : " + extractor.urlCount(email));
            System.out.println("Free Email : " + extractor.isFreeEmail(email));
            System.out.println("Subject Length : " + extractor.subjectLength(email));
            System.out.println("Body Length : " + extractor.bodyLength(email));
            System.out.println("Keyword Count : " + extractor.keywordCount(email));
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DetectorGUI());
    }
}