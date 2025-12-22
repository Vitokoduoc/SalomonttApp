package model.unidades;

import model.interfaces.Registrable;

/**
 * Representa una unidad operativa dentro de la empresa SalmonApp.
 * Superclase de CentroCultivo y PlantaProceso.
 *
 * Autor: Víctor Valenzuela
 */
public class UnidadOperativa implements Registrable, Comparable<UnidadOperativa> {

    protected String nombre;
    protected String comuna;

    public UnidadOperativa(String nombre, String comuna) {
        setNombre(nombre);
        setComuna(comuna);
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la unidad no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getComuna() {
        return comuna;
    }

    protected void setComuna(String comuna) {
        if (comuna == null || comuna.isBlank()) {
            throw new IllegalArgumentException("La comuna no puede estar vacía.");
        }
        this.comuna = comuna.trim();
    }

    /**
     * Información básica de la unidad operativa.
     */
    public void mostrarInformacion() {
        System.out.println(this);
    }

    /**
     * Implementación del método genérico de la interfaz Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    /**
     * Registro simbólico de la unidad operativa.
     */
    @Override
    public void registrar() {
        System.out.println("Unidad operativa registrada: " + nombre);
    }

    /**
     * Orden natural de las unidades operativas por nombre (alfabético).
     */
    @Override
    public int compareTo(UnidadOperativa otra) {
        if (otra == null) {
            return 1;
        }
        return this.nombre.compareToIgnoreCase(otra.nombre);
    }

    /**
     * Representación base tabular de la unidad operativa.
     */
    @Override
    public String toString() {
        return String.format(
                "%-18s | %-12s |",
                nombre,
                comuna
        );
    }
}
