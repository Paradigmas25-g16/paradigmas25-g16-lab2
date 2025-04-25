package parser;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser{

    public static String getValueFromJsonFile(String filePath, String key){
        try(FileReader reader = new FileReader(filePath)){

            JSONTokener tockener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tockener);
            JSONObject fisrObject = jsonArray.getJSONObject(0);
            return fisrObject.optString(key,null);

        } catch (IOException e){
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());

        } catch (Exception e){
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            
        }
        return null;
    }
}
