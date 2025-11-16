package ui;

import data.GestorDatos;
import model.CentroCultivo;

import java.util.List;

/**
 * Clase principal del proyecto. Gestiona la carga de datos desde el archivo
 * y muestra la información en formato tabular para una lectura más clara.
 *
 * Autor: Víctor Valenzuela
 */
public class Main {

    public static void main(String[] args) {

        GestorDatos gestor = new GestorDatos();
        List<CentroCultivo> centros = null;

        try {
            centros = gestor.cargarDatos("datosCentros.txt");
        } catch (Exception e) {
            System.out.println("Error crítico al cargar los datos: " + e.getMessage());
            return;
        }

        if (centros == null || centros.isEmpty()) {
            System.out.println("No se pudieron cargar datos válidos desde el archivo.");
            return;
        }

        imprimirTabla(centros);

        System.out.println("\n=== FILTRO: CENTROS CON PRODUCCIÓN > 1500 ===\n");
        centros.stream()
                .filter(c -> c.getProduccion() > 1500)
                .forEach(Main::imprimirFila);
    }

    /**
     * Imprime la tabla completa con encabezados.
     */
    private static void imprimirTabla(List<CentroCultivo> lista) {

        System.out.println("====================================================================================================");
        System.out.println("                                   LISTA COMPLETA DE CENTROS DE CULTIVO");
        System.out.println("====================================================================================================");

        System.out.printf("%-20s | %-15s | %-11s | %-27s | %-12s | %-8s%n",
                "CENTRO", "COMUNA", "PRODUCCIÓN", "PRODUCTO", "TIPO", "PRECIO");

        System.out.println("----------------------------------------------------------------------------------------------------");

        lista.forEach(Main::imprimirFila);
    }

    /**
     * Imprime una fila formateada de la tabla.
     */
    private static void imprimirFila(CentroCultivo c) {
        System.out.printf("%-20s | %-15s | %-11d | %-27s | %-12s | %-8.1f%n",
                c.getNombreCentro(),
                c.getComuna(),
                c.getProduccion(),
                c.getProducto().getNombreProducto(),
                c.getProducto().getTipoProducto(),
                c.getProducto().getPrecioProducto());
    }

}
