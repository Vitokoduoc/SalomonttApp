package model.persona;

/**
 * Representa un RUT chileno como objeto de valor.
 * Encapsula validación básica y normalización.
 *
 * Autor: Víctor Valenzuela
 */
public class Rut {

    private String rut;

    public Rut() {
    }

    public Rut(String rut) {
        setRut(rut);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (rut == null || rut.isBlank()) {
            throw new IllegalArgumentException("El RUT no puede estar vacío.");
        }

        // Normalización básica
        this.rut = rut.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return rut;
    }
}

