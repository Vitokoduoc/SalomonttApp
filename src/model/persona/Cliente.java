package model.persona;

import model.interfaces.Registrable;
import model.interfaces.MetodoPago;

/**
 * Representa un cliente del sistema SalmonttApp.
 * Extiende a Persona e implementa Registrable.
 *
 * Autor: Víctor Valenzuela
 */
public class Cliente extends Persona implements Registrable {

    private String idCliente;
    private MetodoPago metodoPago;

    public Cliente(String nombre,
                   Rut rut,
                   String email,
                   Direccion direccion,
                   String idCliente,
                   MetodoPago metodoPago) {

        super(nombre, rut, email, direccion);
        setIdCliente(idCliente);
        setMetodoPago(metodoPago);
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El idCliente no puede estar vacío.");
        }
        this.idCliente = idCliente.trim();
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago no puede ser null.");
        }
        this.metodoPago = metodoPago;
    }

    /**
     * Implementación del método genérico de la interfaz Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    /**
     * Registro simbólico del cliente.
     */
    @Override
    public void registrar() {
        System.out.println("Cliente registrado: " + idCliente);
    }

    /**
     * Representación tabular del cliente para salida en consola.
     */
    @Override
    public String toString() {
        return String.format(
                "%-12s | %-18s | %-12s | %-25s | %-15s |",
                idCliente,
                getNombre(),
                getRut(),
                getEmail(),
                metodoPago.getClass().getSimpleName()
        );
    }
}
