package parser;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.w3c.dom.*;

/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {

    /**
     * Set document interface from a XML File
     * @param xmlString
     * @return A Document interface.
     * @throws Exception If there is any parse errors
     */
    public Document xmlToDoc(String xmlString){
        try{
            ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)); //convierte String en inputStream    
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            doc.getDocumentElement().normalize();   //Elimino carateres como "\n" extras

            return doc;
        }catch (Exception e){
            System.err.println("Error parsing xml to doc: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get the JSONOArray from a Json file
     * @param filepath
     * @return A JSONArray.
     * @throws JSONException If there is not file to reed the return is null
     */
    public JSONArray JsonFileToJsonArray(String filepath){
        try(FileReader reader = new FileReader(filepath)){
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);

            return array;
        }catch(Exception e){
            System.err.println("Error parsing JsonFile to JSONArray: " + e.getMessage());
        }
        return null;
    }
}
