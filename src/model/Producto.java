package model;

/**
 * Representa un producto que puede estar asociado a un centro de cultivo.
 * Almacena información básica como nombre, tipo y precio.
 *
 * @author Victor Valenzuela
 */
public class Producto {

    /**
     * atributos del producto
     */
    private String nombreProducto;
    private String tipoProducto;
    private double precioProducto;

    /**
     * Constructor vacío. Permite crear un objeto {@code Producto} sin inicializar sus
     * atributos; estos pueden ser configurados posteriormente mediante los métodos setter.
     */
    public Producto() {
    }

    /**
     * Constructor que inicializa todos los atributos del producto.
     * Aplica validaciones mínimas sobre los parámetros recibidos.
     *
     * @param nombreProducto nombre del producto; no puede ser nulo ni vacío
     * @param tipoProducto   tipo o categoría del producto; no puede ser nulo ni vacío
     * @param precioProducto precio del producto; no puede ser negativo
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public Producto(String nombreProducto, String tipoProducto, double precioProducto) {
        setNombreProducto(nombreProducto);
        setTipoProducto(tipoProducto);
        setPrecioProducto(precioProducto);
    }

    /**
     * getter y setter de los atributos del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.nombreProducto = nombreProducto.trim();
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        if (tipoProducto == null || tipoProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto no puede estar vacío.");
        }
        this.tipoProducto = tipoProducto.trim();
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        if (precioProducto < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        this.precioProducto = precioProducto;
    }

    /**
     * Retorna una representación en texto del producto.
     *
     * @return cadena con los datos del producto
     */
    @Override
    public String toString() {
        return "Producto [nombreProducto=" + nombreProducto +
                ", tipoProducto=" + tipoProducto +
                ", precioProducto=" + precioProducto + "]";
    }
}

