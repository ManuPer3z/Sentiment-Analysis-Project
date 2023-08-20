package com.misentiment.gui;

import javax.swing.*;
import com.misentiment.analyzer.SentimentAnalysis;
import twitter4j.*;
import java.util.List;
import java.util.ArrayList;

public class SentimentAnalysisGUI extends JFrame {

    // Components for the GUI
    private JTextField inputTextField;
    private JButton analyzeButton;
    private JTextArea resultTextArea;

    public SentimentAnalysisGUI() {
        setTitle("Sentiment Analysis");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        inputTextField = new JTextField(30);
        analyzeButton = new JButton("Analyze");
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        add(inputTextField);
        add(analyzeButton);
        add(new JScrollPane(resultTextArea));

        analyzeButton.addActionListener(e -> analyzeTweets());

        setVisible(true);
    }

    private void analyzeTweets() {
        SentimentAnalysis analyzer = new SentimentAnalysis();
        
        List<String> tweets = fetchTweets(inputTextField.getText());
        
        StringBuilder results = new StringBuilder();

        for (String tweet : tweets) {
            String sentiment = analyzer.analyze(tweet);
            results.append(tweet).append(" - Sentiment: ").append(sentiment).append("\n");
        }

        resultTextArea.setText(results.toString());
    }

    private List<String> fetchTweets(String queryStr) {
        List<String> tweetTexts = new ArrayList<>();
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(queryStr);
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            
            for (Status tweet : tweets) {
                tweetTexts.add("@" + tweet.getUser().getScreenName() + ": " + tweet.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return tweetTexts;
    }

    public static void main(String[] args) {
        new SentimentAnalysisGUI();
    }
}
