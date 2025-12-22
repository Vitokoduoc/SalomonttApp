package model.interfaces;

/**
 * Interfaz que define el comportamiento común para todas las entidades
 * gestionables del sistema SalmonttApp.
 *
 * Las clases que implementen esta interfaz pueden ser registradas
 * y mostrar sus datos de forma polimórfica.
 *
 * Autor: Víctor Valenzuela
 */
public interface Registrable {

    /**
     * Registra la entidad en el sistema.
     * En este proyecto se utiliza como acción lógica/simbólica.
     */
    default void registrar() {
        System.out.println("Entidad registrada correctamente.");
    }

    /**
     * Muestra los datos relevantes de la entidad.
     */
    void mostrarDatos();
}
