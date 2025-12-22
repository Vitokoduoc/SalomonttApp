package model.persona;

import model.interfaces.Registrable;
import model.unidades.UnidadOperativa;

/**
 * Representa a un colaborador interno de la empresa.
 *
 * Autor: Víctor Valenzuela
 */
public class Colaborador extends Persona implements Registrable {

    private UnidadOperativa unidadOperativa;
    private String area;
    private String cargo;

    public Colaborador(String nombre,
                       Rut rut,
                       String email,
                       Direccion direccion,
                       UnidadOperativa unidadOperativa,
                       String area,
                       String cargo) {
        super(nombre, rut, email, direccion);
        setUnidadOperativa(unidadOperativa);
        setArea(area);
        setCargo(cargo);
    }

    public UnidadOperativa getUnidadOperativa() {
        return unidadOperativa;
    }

    public void setUnidadOperativa(UnidadOperativa unidadOperativa) {
        if (unidadOperativa == null) {
            throw new IllegalArgumentException("La unidad operativa no puede ser null.");
        }
        this.unidadOperativa = unidadOperativa;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if (area == null || area.isBlank()) {
            throw new IllegalArgumentException("El área no puede estar vacía.");
        }
        this.area = area.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }
        this.cargo = cargo.trim();
    }

    /**
     * Implementación de mostrarDatos() para el contrato de Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    /**
     * Implementación de registrar() para el contrato de Registrable.
     */
    @Override
    public void registrar() {
        System.out.println("Colaborador registrado: " + getNombre());
    }

    /**
     * Representación tabular del colaborador para salida en consola.
     */
    @Override
    public String toString() {
        return String.format(
                "%-18s | %-12s | %-20s | %-15s | %-15s |",
                getNombre(),
                getRut(),
                cargo,
                area,
                unidadOperativa.getNombre()
        );
    }
}
