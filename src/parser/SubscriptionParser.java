package parser;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.json.JSONArray;

import subscription.*;

public class SubscriptionParser extends GeneralParser{
    
    public static JSONArray readJsonArrayFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            return new JSONArray(tokener);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return null;
    }
    
    public static Subscription createSubscriptionFromJsonArray(JSONArray array) {
        if (array == null || array.length() < 2) {
            System.err.println("Invalid or incomplete JSON array.");
            return null;
        }
    
        try {
            JSONObject rssJsonObject = array.getJSONObject(0);
            JSONObject redditJsonObject = array.getJSONObject(1);
    
            // rss
            String rssUrl = rssJsonObject.getString("url");
            String rssUrlType = rssJsonObject.getString("urlType");
            JSONArray rssParams = rssJsonObject.getJSONArray("urlParams");
    
            SingleSubscription rssSingleSubscription = new SingleSubscription(rssUrl, null, rssUrlType);
            for (int i = 0; i < rssParams.length(); i++) {
                rssSingleSubscription.setUlrParams(rssParams.getString(i));
            }
    
            // reddit
            String redditUrl = redditJsonObject.getString("url");
            String redditUrlType = redditJsonObject.getString("urlType");
            JSONArray redditParams = redditJsonObject.getJSONArray("urlParams");
    
            SingleSubscription redditSingleSubscription = new SingleSubscription(redditUrl, null, redditUrlType);
            for (int i = 0; i < redditParams.length(); i++) {
                redditSingleSubscription.setUlrParams(redditParams.getString(i));
            }
    
            // final Subscription
            Subscription subs = new Subscription(null);
            subs.addSingleSubscription(rssSingleSubscription);
            subs.addSingleSubscription(redditSingleSubscription);
    
            return subs;
        } catch (Exception e) {
            System.err.println("Error creating Subscription: " + e.getMessage());
        }
        return null;
    }
    
}
