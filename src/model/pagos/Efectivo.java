package model.pagos;

import model.interfaces.MetodoPago;

/**
 * Representa el método de pago en efectivo.
 * Implementa la interfaz MetodoPago.
 *
 * Autor: Víctor Valenzuela
 */
public class Efectivo implements MetodoPago {

    @Override
    public String procesarPago(double monto) {
        return "Pago en efectivo por $" + monto + " procesado correctamente.";
    }

    @Override
    public String toString() {
        return "Efectivo";
    }
}
