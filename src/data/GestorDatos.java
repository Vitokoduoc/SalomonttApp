package data;

import model.CentroCultivo;
import model.Producto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de cargar información estructurada desde un archivo
 * de recursos y transformarla en una lista de objetos {@link CentroCultivo}.
 * Se procesan tanto los datos del centro como del producto asociado,
 * respetando el modelo de composición definido en la aplicación.
 *
 * Autor: Víctor Valenzuela
 */
public class GestorDatos {

    /**
     * Carga los datos de centros productivos desde un archivo ubicado en el classpath.
     * Cada línea es analizada para construir el producto correspondiente y asociarlo
     * al centro de cultivo, manteniendo una composición adecuada.
     *
     * @param rutaArchivo archivo de recursos con la estructura:
     *                    nombreCentro;comuna;produccion;nombreProducto;tipoProducto;precioProducto
     * @return lista con los centros cargados
     */
    public List<CentroCultivo> cargarDatos(String rutaArchivo) {

        List<CentroCultivo> datos = new ArrayList<>();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);

            if (inputStream == null) {
                throw new IllegalArgumentException("No se encontró el archivo: " + rutaArchivo);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                String linea;

                while ((linea = reader.readLine()) != null) {

                    if (linea.trim().isEmpty()) continue;

                    String[] partes = linea.split(";");

                    // Validación de estructura
                    if (partes.length != 6) {
                        System.out.println("Línea con formato incorrecto, se omite: " + linea);
                        continue;
                    }

                    // Datos del Centro
                    String nombreCentro = partes[0].trim();
                    String comuna = partes[1].trim();
                    int produccion = Integer.parseInt(partes[2].trim());

                    // Datos del Producto asociado
                    String nombreProducto = partes[3].trim();
                    String tipoProducto = partes[4].trim();
                    double precioProducto = Double.parseDouble(partes[5].trim());

                    // Construcción del Producto
                    Producto producto = new Producto(nombreProducto, tipoProducto, precioProducto);

                    // Construcción del Centro con el Producto asociado
                    CentroCultivo centro = new CentroCultivo(nombreCentro, comuna, producto, produccion);

                    datos.add(centro);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }

        return datos;
    }
}

