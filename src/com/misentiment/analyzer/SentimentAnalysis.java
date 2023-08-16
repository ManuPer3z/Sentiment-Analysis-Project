package com.misentiment.analyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class SentimentAnalysis {

    private StanfordCoreNLP pipeline;

    public SentimentAnalysis() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public String analyze(String text) {
        Annotation annotation = pipeline.process(text);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentimentValue = RNNCoreAnnotations.getPredictedClass(tree);
            return toSentimentLabel(sentimentValue); // devuelve el sentimiento de la primera frase. Puedes modificar esto para manejar múltiples frases.
        }
        return "Neutral";  // Devuelve Neutral si no se pudo determinar un sentimiento
    }

    private String toSentimentLabel(int sentimentValue) {
        switch(sentimentValue) {
            case 0: return "Muy negativo";
            case 1: return "Negativo";
            case 2: return "Neutral";
            case 3: return "Positivo";
            case 4: return "Muy positivo";
            default: return "Desconocido";
        }
    }

    public static void main(String[] args) {
        SentimentAnalysis analyzer = new SentimentAnalysis();
        String text = "I love Java!";
        String sentiment = analyzer.analyze(text);
        System.out.println("Sentiment for the text '" + text + "' is: " + sentiment);
    }
}