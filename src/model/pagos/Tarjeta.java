package model.pagos;

import model.interfaces.MetodoPago;

/**
 * Representa un método de pago mediante tarjeta.
 * Implementa la interfaz MetodoPago.
 *
 * Autor: Víctor Valenzuela
 */
public class Tarjeta implements MetodoPago {

    private String numeroTarjeta;
    private String titular;
    private String fechaVencimiento;

    /**
     * Constructor principal de Tarjeta.
     *
     * @param numeroTarjeta número de la tarjeta
     * @param titular nombre del titular
     * @param fechaVencimiento fecha de vencimiento (MM/AA)
     */
    public Tarjeta(String numeroTarjeta, String titular, String fechaVencimiento) {
        setNumeroTarjeta(numeroTarjeta);
        setTitular(titular);
        setFechaVencimiento(fechaVencimiento);
    }

    /**
     * Constructor sin parámetros para escenarios de carga genérica
     * (por ejemplo, datos leídos desde archivo donde no se detalla la tarjeta).
     */
    public Tarjeta() {
        // Valores por defecto “genéricos”
        this.numeroTarjeta = "0000-0000-0000-0000";
        this.titular = "Titular no definido";
        this.fechaVencimiento = "01/30";
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        if (numeroTarjeta == null || numeroTarjeta.isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede estar vacío.");
        }
        this.numeroTarjeta = numeroTarjeta.trim();
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        }
        this.titular = titular.trim();
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede estar vacía.");
        }
        this.fechaVencimiento = fechaVencimiento.trim();
    }

    /**
     * Procesa el pago con tarjeta.
     */
    @Override
    public String procesarPago(double monto) {
        return "Pago con tarjeta por $" + monto + " procesado correctamente.";
    }

    /**
     * Representación simple del método de pago.
     */
    @Override
    public String toString() {
        return "Tarjeta";
    }
}

