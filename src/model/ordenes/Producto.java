package model.ordenes;

import model.interfaces.Registrable;
import model.unidades.UnidadOperativa;

/**
 * Representa un producto vinculado a una unidad operativa.
 *
 * Autor: Víctor Valenzuela
 */
public class Producto implements Registrable {

    private String nombre;
    private String comuna;
    private String tipoProducto;
    private double cantidad;
    private UnidadOperativa unidadOrigen;

    public Producto(String nombre,
                    String comuna,
                    String tipoProducto,
                    double cantidad,
                    UnidadOperativa unidadOrigen) {

        setNombre(nombre);
        setComuna(comuna);
        setTipoProducto(tipoProducto);
        setCantidad(cantidad);
        setUnidadOrigen(unidadOrigen);
    }

    public String getNombre() { return nombre; }
    public String getComuna() { return comuna; }
    public String getTipoProducto() { return tipoProducto; }
    public double getCantidad() { return cantidad; }
    public UnidadOperativa getUnidadOrigen() { return unidadOrigen; }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre inválido.");
        this.nombre = nombre.trim();
    }

    public void setComuna(String comuna) {
        if(comuna == null || comuna.isBlank())
            throw new IllegalArgumentException("Comuna inválida.");
        this.comuna = comuna.trim();
    }

    public void setTipoProducto(String tipoProducto) {
        if(tipoProducto == null || tipoProducto.isBlank())
            throw new IllegalArgumentException("Tipo inválido.");
        this.tipoProducto = tipoProducto.trim();
    }

    public void setCantidad(double cantidad) {
        if(cantidad <= 0)
            throw new IllegalArgumentException("Cantidad debe ser > 0");
        this.cantidad = cantidad;
    }

    public void setUnidadOrigen(UnidadOperativa unidadOrigen) {
        if(unidadOrigen == null)
            throw new IllegalArgumentException("Unidad origen requerida.");
        this.unidadOrigen = unidadOrigen;
    }

    /**
     * Implementa el método de interfaz Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.printf(
                "%-18s | %-12s | %-20s | %-8s | %-15s |%n",
                nombre,
                comuna,
                tipoProducto,
                cantidad,
                unidadOrigen.getNombre()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "%-18s | %-12s | %-20s | %-8s | %-15s |",
                nombre,
                comuna,
                tipoProducto,
                cantidad,
                unidadOrigen.getNombre()
        );
    }
}
