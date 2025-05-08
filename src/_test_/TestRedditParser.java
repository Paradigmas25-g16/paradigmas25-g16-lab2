package _test_;

import httpRequest.httpRequester;
import parser.RedditParser;
import feed.Feed;

public class TestRedditParser {
    public static void main(String[] args) {
     
        String url = "https://www.reddit.com/r/Sales/hot/.json?count=100";

        httpRequester requester = new httpRequester();
        String jsoString = requester.getFeedReedit(url);

        RedditParser parser = new RedditParser();
        Feed feed = parser.parse(jsoString);

        feed.prettyPrint();
    }
}
