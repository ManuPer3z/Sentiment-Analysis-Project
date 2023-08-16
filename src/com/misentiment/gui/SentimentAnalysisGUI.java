package com.misentiment.gui;

import javax.swing.*;
import com.misentiment.analyzer.SentimentAnalysis;

/**
 * SentimentAnalysisGUI class provides a simple graphical interface for sentiment analysis.
 */
public class SentimentAnalysisGUI extends JFrame {
    // Components for the GUI
    private JTextArea inputTextArea;
    private JButton analyzeButton;
    private JLabel resultLabel;

    /**
     * Constructor initializes the GUI components and layouts.
     */
    public SentimentAnalysisGUI() {
        // Setting the title of the window
        setTitle("Sentiment Analysis");
        
        // Setting default size of the window
        setSize(400, 300);
        
        // Ensure application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set layout for components
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize text area for user input
        inputTextArea = new JTextArea(10, 30);
        
        // Initialize the analyze button
        analyzeButton = new JButton("Analyze");
        
        // Initialize the label to display the analysis result
        resultLabel = new JLabel("Sentiment: Neutral");

        // Add components to the frame
        add(new JScrollPane(inputTextArea));
        add(analyzeButton);
        add(resultLabel);

        // Set button's action to analyze the input text when clicked
        analyzeButton.addActionListener(e -> analyzeText());

        // Display the window
        setVisible(true);
    }

    /**
     * Analyzes the text from the text area and updates the result label with the sentiment.
     */
    private void analyzeText() {
        // Create an instance of the sentiment analyzer
        SentimentAnalysis analyzer = new SentimentAnalysis();
        
        // Analyze the sentiment of the provided text
        String sentiment = analyzer.analyze(inputTextArea.getText());
        
        // Update the result label with the analyzed sentiment
        resultLabel.setText("Sentiment: " + sentiment);
    }

    /**
     * Main method to launch the GUI.
     */
    public static void main(String[] args) {
        new SentimentAnalysisGUI();
    }
}