package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para lectura de archivos de texto.
 *
 * Autor: Víctor Valenzuela
 */
public class LectorArchivo {

    public static List<String> leerArchivo(String ruta) {

        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(ruta), StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                // Ignorar líneas vacías o comentarios
                if (linea.isBlank() || linea.startsWith("#")) {
                    continue;
                }

                lineas.add(linea);
            }

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return lineas;
    }
}
