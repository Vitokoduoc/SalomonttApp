package ui;

import data.GestorUnidades;

/**
 * Clase principal del sistema. Su función es inicializar el programa
 * y mostrar por consola todas las unidades operativas creadas por
 * {@link GestorUnidades}, utilizando el formato de tabla definido en
 * los métodos {@code toString()} de cada subclase.
 *
 * <p>
 * Esta clase demuestra la correcta implementación de la jerarquía de clases,
 * el uso de herencia, sobrescritura y la impresión formateada de datos.
 * </p>
 *
 */
public class Main {

    public static void main(String[] args) {

        GestorUnidades gestor = new GestorUnidades();

        System.out.println("\n============== Unidades Operativas ==============");
        System.out.println(String.format(
                "%-18s | %-10s | %-13s |",
                "Nombre", "Comuna", "Producción"
        ));
        System.out.println("=================================================");

        for (Object obj : gestor.crearUnidades()) {
            System.out.println(obj.toString());
        }

        System.out.println("=================================================");
    }
}

