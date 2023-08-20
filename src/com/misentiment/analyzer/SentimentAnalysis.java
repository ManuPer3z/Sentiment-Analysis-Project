package com.misentiment.analyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

/**
 * SentimentAnalysis class to analyze sentiments of given text using Stanford NLP.
 */
public class SentimentAnalysis {

    private StanfordCoreNLP pipeline;

    /**
     * Constructor that initializes the NLP pipeline with necessary properties.
     */
    public SentimentAnalysis() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Analyzes the sentiment of a given text and returns the sentiment label.
     * 
     * @param text The text to be analyzed.
     * @return Sentiment label (e.g., "Positive", "Neutral").
     */
    public String analyze(String text) {
        Annotation annotation = pipeline.process(text);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentimentValue = RNNCoreAnnotations.getPredictedClass(tree);
            return toSentimentLabel(sentimentValue);
        }
        return "Neutral";
    }

    /**
     * Converts a numerical sentiment value to a corresponding string label.
     * 
     * @param sentimentValue The numerical sentiment value.
     * @return The string label of the sentiment.
     */
    private String toSentimentLabel(int sentimentValue) {
        switch(sentimentValue) {
            case 0: return "Very Negative";
            case 1: return "Negative";
            case 2: return "Neutral";
            case 3: return "Positive";
            case 4: return "Very Positive";
            default: return "Unknown";
        }
    }

    /**
     * Main method for testing purposes.
     */
    public static void main(String[] args) {
        SentimentAnalysis analyzer = new SentimentAnalysis();
        String text = "I love Java!";
        String sentiment = analyzer.analyze(text);
        System.out.println("Sentiment for the text '" + text + "' is: " + sentiment);
    }
}