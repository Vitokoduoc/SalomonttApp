package model.interfaces;

/**
 * Interfaz que define el comportamiento común para los distintos
 * métodos de pago del sistema SalmonttApp.
 *
 * Las clases que implementen esta interfaz deben proporcionar
 * la lógica necesaria para procesar un pago.
 *
 * Autor: Víctor Valenzuela
 */
public interface MetodoPago {

    /**
     * Procesa un pago por un monto determinado.
     *
     * @param monto monto a pagar
     * @return mensaje con el resultado del pago
     */
    String procesarPago(double monto);
}
