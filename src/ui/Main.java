package ui;

import data.GestorUnidades;
import model.UnidadOperativa;

import java.util.List;

/**
 * Clase principal del sistema. Su función es inicializar el programa
 * y mostrar por consola todas las unidades operativas creadas por
 * {@link GestorUnidades}, utilizando polimorfismo sobre la jerarquía
 * {@link UnidadOperativa}.
 *
 * <p>
 * Esta clase demuestra la correcta implementación de la jerarquía de clases,
 * el uso de herencia, sobrescritura y el recorrido de una colección genérica
 * mediante referencias de la superclase.
 * </p>
 */
public class Main {

    public static void main(String[] args) {

        GestorUnidades gestor = new GestorUnidades();
        List<UnidadOperativa> unidades = gestor.crearUnidades();

        System.out.println("\n============= Unidades Operativas ==============");

        // Recorrido polimórfico: se invoca mostrarInformacion() desde
        // una referencia del tipo UnidadOperativa.
        for (UnidadOperativa unidad : unidades) {
            unidad.mostrarInformacion();
        }

        System.out.println("=================================================");
    }
}
