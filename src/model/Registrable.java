package model;

/**
 * Interfaz que define el comportamiento común para todas las entidades
 * gestionables del sistema SalmonttApp.
 *
 * <p>Las clases que implementen esta interfaz deben proporcionar su propia
 * versión del método {@code mostrarResumen()}, el cual permite mostrar
 * información resumida de cada instancia.</p>
 */
public interface Registrable {

    /**
     * Muestra un resumen de la información relevante de la entidad.
     */
    void mostrarResumen();
}
