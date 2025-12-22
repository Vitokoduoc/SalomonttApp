package model.ordenes;

import model.interfaces.MetodoPago;
import model.interfaces.Registrable;
import model.persona.Cliente;

import java.time.LocalDate;

public class OrdenDeCompra implements Registrable {

    private String idOrden;
    private Cliente cliente;
    private Producto producto;
    private double cantidad;
    private MetodoPago metodoPago;
    private LocalDate fecha;

    public OrdenDeCompra(String idOrden,
                         Cliente cliente,
                         Producto producto,
                         double cantidad,
                         MetodoPago metodoPago,
                         LocalDate fecha) {

        setIdOrden(idOrden);
        setCliente(cliente);
        setProducto(producto);
        setCantidad(cantidad);
        setMetodoPago(metodoPago);
        setFecha(fecha);
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s | %-20s | %-25s | %-7.0f | %-12s | %-12s |",
                idOrden,
                cliente.getNombre(),
                producto.getNombre(),
                cantidad,
                (metodoPago != null ? metodoPago.getClass().getSimpleName() : "N/A"),
                fecha
        );
    }
}

