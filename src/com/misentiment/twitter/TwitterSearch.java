package com.misentiment.twitter;

import twitter4j.*;
import java.util.List;

public class TwitterSearch {
    public static void main(String[] args) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query("palabra_clave");
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            
            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + ": " + tweet.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    }
}