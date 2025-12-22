package model.unidades;


import model.interfaces.Registrable;

/**
 * Representa un centro de cultivo perteneciente a la empresa.
 *
 * Autor: Víctor Valenzuela
 */
public class CentroCultivo extends UnidadOperativa implements Registrable    {

    private int toneladasProduccion;

    public CentroCultivo(String nombre, String comuna, int toneladasProduccion) {
        super(nombre, comuna);
        setToneladasProduccion(toneladasProduccion);
    }

    public int getToneladasProduccion() {
        return toneladasProduccion;
    }

    public void setToneladasProduccion(int toneladasProduccion) {
        if (toneladasProduccion <= 0) {
            throw new IllegalArgumentException("Las toneladas de producción deben ser mayores a cero.");
        }
        this.toneladasProduccion = toneladasProduccion;
    }

    @Override
    public void mostrarInformacion() {
        mostrarDatos(); // Consistencia: una sola salida estándar
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format(
                "%-18s | %-12s | %-15s |",
                getNombre(),
                getComuna(),
                toneladasProduccion + " t"
        );
    }
}
