package model;

/**
 * Representa una unidad operativa dentro de la empresa SalmonApp.
 *
 * <p>
 * Esta clase actúa como superclase para distintos tipos de unidades,
 * tales como {@code CentroCultivo} y {@code PlantaProceso}. Contiene
 * los atributos comunes que comparten todas las unidades operativas,
 * como su nombre y la comuna donde se ubican.
 * </p>
 *
 * <p>
 * Su propósito es permitir reutilizar atributos comunes en subclases,
 * promoviendo la herencia y la extensibilidad del sistema.
 * </p>
 */
public class UnidadOperativa {

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
     * Este método puede ser sobrescrito por las subclases para
     * agregar más detalles específicos.
     */
    public void mostrarInformacion() {
        System.out.println("Unidad Operativa: " + nombre + ", Comuna: " + comuna);
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
}

