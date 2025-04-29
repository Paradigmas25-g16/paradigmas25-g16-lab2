package httpRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class httpRequester {
	
	public String getFeedRss(String urlFeed){
		try {
			URL url = new URL(urlFeed);  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
		
			//Dado el input de la request, lo mandamos a un buffer
			BufferedReader input = new BufferedReader(
  				new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = input.readLine()) != null) {
    			content.append(inputLine);
			}
			input.close();

			//Pasamos del buffer a un string para poder retornarlo
			String feedRssXml = content.toString();  	

        	// int status = connection.getResponseCode();
        	connection.disconnect();  					// Cerrar la conexión
			return feedRssXml;
		}
		catch (Exception e) {
            e.printStackTrace();
            return null;  // Manejo básico de errores
        }
	}

	public String getFeedReedit(String urlFeed) {
		String feedReeditJson = null;
		return feedReeditJson;
	}

}

