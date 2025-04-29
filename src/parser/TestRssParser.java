package parser;

import java.util.List;
import parser.RssParser.RssItem;

public class TestRssParser {
    public static void main(String[] args) {
        // 1. XML de prueba (feed RSS simulado)
        String testRss = """
            <?xml version="1.0"?>
            <rss>
                <channel>
                    <item>
                        <title>Noticia 1</title>
                        <description>Descripción 1</description>
                        <pubDate>2025-05-15</pubDate>
                        <link>https://ejemplo.com/1</link>
                    </item>
                </channel>
            </rss>
            """;

        // 2. Probar el parser
        System.out.println("[TEST] Iniciando pruebas...");
        // Corrección: Crear instancia primero
        RssParser parser = new RssParser();  // Instancia
        List<RssParser.RssItem> items = parser.parser(testRss);  // Llamada correcta

        // 3. Verificaciones manuales
        if (items == null) {
            System.err.println("❌ Error: El parser retornó null");
            return;
        }

        if (items.size() != 1) {
            System.err.println("❌ Error: Se esperaba 1 item, pero se obtuvieron " + items.size());
        } else {
            RssItem item = items.get(0);
            boolean passed = true;

            // Verificar cada campo
            if (!"Noticia 1".equals(item.getTitle())) {
                System.err.println("❌ Error en título: " + item.getTitle());
                passed = false;
            }

            if (!"Descripción 1".equals(item.getDescription())) {
                System.err.println("❌ Error en descripción: " + item.getDescription());
                passed = false;
            }

            if (passed) {
                System.out.println("✅ ¡Test pasado correctamente!");
                System.out.println("Item parseado:");
                System.out.println("Título: " + item.getTitle());
                System.out.println("Enlace: " + item.getLink());
            }
        }
    }
}