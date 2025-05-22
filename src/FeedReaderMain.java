public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}
		
	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {
    		// 1. Leer archivo de suscripción por defecto
    		
			System.out.println("Leyendo el archivo de suscripcion por defecto");
			String jsonpath = "config/subscriptions.json";
    		parser.SubscriptionParser parser = new parser.SubscriptionParser();
    		subscription.Subscription subs = parser.parse(jsonpath);

    		// 2. Mostrar opciones y pedir input al usuario
    		java.util.List<subscription.SingleSubscription> subsList = subs.getSubscriptionsList();
    		System.out.println("Elija una URL (1-" + subsList.size() + "):");
    		for (int i = 0; i < subsList.size(); i++) {
    		    System.out.println("(" + (i + 1) + ") " + subsList.get(i).getUrl());
    		}
    		System.out.print("Opción: ");
    		java.util.Scanner stdin = new java.util.Scanner(System.in);
    		int inputUrl = stdin.nextInt();
    		if (inputUrl < 1 || inputUrl > subsList.size()) {
    		    System.err.println("Opción fuera de rango");
    		    stdin.close();
    		    return;
    		}
    		subscription.SingleSubscription singleSub = subs.getSingleSubscription(inputUrl - 1);

    		System.out.println("Elija un parámetro para '%s' (1-" + singleSub.getUlrParamsSize() + "):");
    		for (int i = 0; i < singleSub.getUlrParamsSize(); i++) {
    		    System.out.println("(" + (i + 1) + ") " + singleSub.getUlrParams(i));
    		}
    		System.out.print("Opción: ");
    		int inputParam = stdin.nextInt();
    		if (inputParam < 1 || inputParam > singleSub.getUlrParamsSize()) {
    		    System.err.println("Opción fuera de rango");
    		    stdin.close();
    		    return;
    		}
    		String urlCompleted = singleSub.getFeedToRequest(inputParam - 1);
    		stdin.close();

    		// 3. Obtener el feed del servidor
    		httpRequest.httpRequester requester = new httpRequest.httpRequester();
    		String feedData = requester.getFeedContent(urlCompleted);

    		// 4. Parsear el feed
    		feed.Feed feed = null;
    		if (urlCompleted.contains("rss.nytimes.com")) {
    		    parser.RssParser rssParser = new parser.RssParser();
    		    feed = rssParser.parse(feedData);
    		} else if (urlCompleted.contains("www.reddit.com")) {
    		    parser.RedditParser redditParser = new parser.RedditParser();
    		    feed = redditParser.parse(urlCompleted);
    		}

    		// 5. Mostrar los artículos
    		if (feed != null) {
    		    feed.prettyPrint();
    		} else {
    		    System.err.println("No se pudo obtener el feed.");
    		}

		} else if (args.length == 1){
            // 1. Leer archivo de suscripción por defecto
			System.out.println("Leyendo el archivo de suscripcion por defecto");
            String jsonpath = "config/subscriptions.json";
            parser.SubscriptionParser parser = new parser.SubscriptionParser();
            subscription.Subscription subs = parser.parse(jsonpath);

            // 2. Mostrar opciones y pedir input al usuario
    		java.util.List<subscription.SingleSubscription> subsList = subs.getSubscriptionsList();
    		System.out.println("Elija una URL (1-" + subsList.size() + "):");
    		for (int i = 0; i < subsList.size(); i++) {
    		    System.out.println("(" + (i + 1) + ") " + subsList.get(i).getUrl());
    		}
    		System.out.print("Opción: ");
    		java.util.Scanner stdin = new java.util.Scanner(System.in);
    		int inputUrl = stdin.nextInt();
    		if (inputUrl < 1 || inputUrl > subsList.size()) {
    		    System.err.println("Opción fuera de rango");
    		    stdin.close();
    		    return;
    		}
    		subscription.SingleSubscription singleSub = subs.getSingleSubscription(inputUrl - 1);

    		System.out.println("Elija un parámetro para '%s' (1-" + singleSub.getUlrParamsSize() + "):");
    		for (int i = 0; i < singleSub.getUlrParamsSize(); i++) {
    		    System.out.println("(" + (i + 1) + ") " + singleSub.getUlrParams(i));
    		}
    		System.out.print("Opción: ");
    		int inputParam = stdin.nextInt();
    		if (inputParam < 1 || inputParam > singleSub.getUlrParamsSize()) {
    		    System.err.println("Opción fuera de rango");
    		    stdin.close();
    		    return;
    		}
    		String urlCompleted = singleSub.getFeedToRequest(inputParam - 1);
    

           	// 3. Obtener el feed del servidor
    		httpRequest.httpRequester requester = new httpRequest.httpRequester();
    		String feedData = requester.getFeedContent(urlCompleted);

            // 4. Parsear el feed
            feed.Feed feed = null;
            if (urlCompleted.contains("rss.nytimes.com")) {
                parser.RssParser rssParser = new parser.RssParser();
                feed = rssParser.parse(feedData);
            } else if (urlCompleted.contains("www.reddit.com")) {
                parser.RedditParser redditParser = new parser.RedditParser();
                feed = redditParser.parse(urlCompleted);
            }

            // 5. Calcular entidades nombradas y mostrar tabla
			/*
			 * Hacer la parte de la heuristica para calcular las entidades nombradas
			 */
    		stdin.close();
        } else {
            printHelp();
        }
	}

}
