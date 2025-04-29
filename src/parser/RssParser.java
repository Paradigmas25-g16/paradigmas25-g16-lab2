package parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

 public class RssParser extends GeneralParser {
    

    public class RssItem {
        private String title;
        private String description;
        private String pubDate;
        private String link;

        // Constructor
        public RssItem(String title, String description, String pubDate, String link) {
            this.title = title;
            this.description = description;
            this.pubDate = pubDate;
            this.link = link;
        }

        // Getters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getPubDate() { return pubDate; }
        public String getLink() { return link; }
    }


    public List<RssItem> parser(String texto) {
        try {      
            //Creating a DocumentBuilder Object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
                 
            //Reading the XML
            Document doc = docBuilder.parse(new InputSource(new StringReader(texto)));
            
            NodeList items = doc.getElementsByTagName("item");
            List<RssItem> rssItems = new ArrayList<>();

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);
                
                RssItem rssItem = new RssItem(
                item.getElementsByTagName("title").item(0).getTextContent(),
                item.getElementsByTagName("description").item(0).getTextContent(),
                item.getElementsByTagName("pubDate").item(0).getTextContent(),
                item.getElementsByTagName("link").item(0).getTextContent()
                );
                rssItems.add(rssItem);
            }      
                
            return rssItems;

         } catch (Exception e) {
            e.printStackTrace();
            return null;
         }
   }
}



