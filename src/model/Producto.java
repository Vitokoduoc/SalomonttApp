package model;

/**
 * Representa un producto gestionado dentro de una unidad operativa,
 * ya sea un centro de cultivo o una planta de proceso.
 *
 * <p>
 * Esta clase hereda de {@link UnidadOperativa} para efectos del modelo
 * académico, permitiendo que el producto sea tratado polimórficamente
 * como una unidad operativa en ciertos contextos.
 * Además, mantiene una referencia explícita a la unidad donde se encuentra.
 * </p>
 */
public class Producto extends UnidadOperativa implements Registrable {

    private String tipoProducto;         // Ej: "Salmón Atlántico", "Trucha"
    private double cantidad;            // cantidad en toneladas o unidades
    private UnidadOperativa unidadOrigen;

    /**
     * Construye un producto asociado a una unidad operativa específica.
     *
     * @param nombre         nombre del producto
     * @param comuna         comuna asociada (puede reutilizarse del origen)
     * @param tipoProducto   tipo o categoría del producto
     * @param cantidad       cantidad disponible o producida
     * @param unidadOrigen   unidad operativa donde se encuentra el producto
     *                       (centro de cultivo o planta de proceso)
     */
    public Producto(String nombre,
                    String comuna,
                    String tipoProducto,
                    double cantidad,
                    UnidadOperativa unidadOrigen) {
        super(nombre, comuna);
        this.tipoProducto = tipoProducto;
        this.cantidad = cantidad;
        this.unidadOrigen = unidadOrigen;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public UnidadOperativa getUnidadOrigen() {
        return unidadOrigen;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(
                "Producto: " + nombre +
                        " | Tipo: " + tipoProducto +
                        " | Cantidad: " + cantidad +
                        " | Origen: " + unidadOrigen.getNombre() +
                        " (" + unidadOrigen.getClass().getSimpleName() + ")"
        );
    }

    @Override
    public void mostrarResumen() {
        System.out.println(
                "Producto: " + nombre +
                        " (" + tipoProducto + "), " +
                        "Origen: " + unidadOrigen.getNombre()
        );
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", cantidad=" + cantidad +
                ", unidadOrigen=" + unidadOrigen.getNombre() +
                '}';
    }
}
