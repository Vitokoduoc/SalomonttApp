package model.pagos;

import model.interfaces.MetodoPago;

/**
 * Representa un método de pago mediante transferencia bancaria.
 * Implementa la interfaz MetodoPago.
 *
 * Autor: Víctor Valenzuela
 */
public class Transferencia implements MetodoPago {

    private String banco;
    private String numeroCuenta;
    private String titular;

    /**
     * Constructor principal.
     */
    public Transferencia(String banco, String numeroCuenta, String titular) {
        setBanco(banco);
        setNumeroCuenta(numeroCuenta);
        setTitular(titular);
    }

    /**
     * Constructor vacío para uso desde archivos .txt
     * y creación genérica desde la GUI.
     */
    public Transferencia() {
        this.banco = "Banco genérico";
        this.numeroCuenta = "00000000";
        this.titular = "Titular no definido";
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        if (banco == null || banco.isBlank()) {
            throw new IllegalArgumentException("El banco no puede estar vacío.");
        }
        this.banco = banco.trim();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío.");
        }
        this.numeroCuenta = numeroCuenta.trim();
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

    @Override
    public String procesarPago(double monto) {
        return "Transferencia por $" + monto + " procesada correctamente.";
    }

    @Override
    public String toString() {
        return "Transferencia";
    }
}
