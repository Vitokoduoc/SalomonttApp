package model;

/**
 * Representa una unidad operativa dentro de la empresa SalmonApp.
 *
 * <p>
 * Esta clase actúa como superclase para distintos tipos de unidades,
 * tales como {@code CentroCultivo} y {@code PlantaProceso}. Contiene
 * los atributos comunes que comparten todas las unidades operativas:
 * nombre y comuna.
 * </p>
 *
 * <p>
 * Implementa la interfaz {@link Registrable} para permitir que las
 * unidades operativas formen parte de colecciones polimórficas que
 * integran distintas entidades del sistema.
 * </p>
 */
public class UnidadOperativa implements Registrable {

    protected String nombre;
    protected String comuna;

    /**
     * Construye una nueva UnidadOperativa con su nombre y comuna.
     *
     * @param nombre nombre de la unidad operativa
     * @param comuna comuna donde está ubicada la unidad
     */
    public UnidadOperativa(String nombre, String comuna) {
        this.nombre = nombre;
        this.comuna = comuna;
    }

    /**
     * Muestra la información básica de la unidad operativa.
     * Puede ser sobrescrito por subclases para personalizar la salida.
     */
    public void mostrarInformacion() {
        System.out.println("Unidad Operativa: " + nombre + ", Comuna: " + comuna);
    }

    /**
     * Implementación general del resumen para la interfaz Registrable.
     * Las subclases pueden sobrescribir este método si requieren
     * mostrar información más específica.
     */
    @Override
    public void mostrarResumen() {
        System.out.println("[Unidad Operativa] " + nombre + " | Comuna: " + comuna);
    }

    /**
     * Obtiene el nombre de la unidad operativa.
     *
     * @return nombre de la unidad operativa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la comuna donde se encuentra la unidad operativa.
     *
     * @return comuna de la unidad operativa
     */
    public String getComuna() {
        return comuna;
    }

    @Override
    public String toString() {
        return "UnidadOperativa{" +
                "nombre='" + nombre + '\'' +
                ", comuna='" + comuna + '\'' +
                '}';
    }
}
