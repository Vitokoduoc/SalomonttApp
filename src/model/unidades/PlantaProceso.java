package model.unidades;

import model.interfaces.Registrable;

/**
 * Representa una planta de proceso dentro de la empresa.
 *
 * Autor: Víctor Valenzuela
 */
public class PlantaProceso extends UnidadOperativa implements Registrable {

    private int capacidadProceso;

    public PlantaProceso(String nombre, String comuna, int capacidadProceso) {
        super(nombre, comuna);
        setCapacidadProceso(capacidadProceso);
    }

    public int getCapacidadProceso() {
        return capacidadProceso;
    }

    public void setCapacidadProceso(int capacidadProceso) {
        if (capacidadProceso <= 0) {
            throw new IllegalArgumentException("La capacidad de proceso debe ser mayor a cero.");
        }
        this.capacidadProceso = capacidadProceso;
    }

    /**
     * Muestra información detallada de la planta de proceso.
     */
    @Override
    public void mostrarInformacion() {
        mostrarDatos(); // Delegar a mostrarDatos para consistencia
    }

    /**
     * Resumen usado en colecciones polimórficas Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.println(this); // Utiliza toString() para representar la planta
    }

    /**
     * Representación tabular de la planta de proceso para salida en consola.
     */
    @Override
    public String toString() {
        return String.format(
                "%-18s | %-12s | %-15s |",
                getNombre(),
                getComuna(),
                capacidadProceso + " t/día"
        );
    }
}
