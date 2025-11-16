package model;

/**
 * Clase que representa un centro de cultivo y sus atributos principales.
 * Cada centro mantiene información relevante como su nombre, comuna,
 * nivel de producción y un producto asociado mediante composición.
 *
 * Autor: Víctor Valenzuela
 */
public class CentroCultivo {


    private String nombreCentro;
    private String comuna;
    private int produccion;
    private Producto producto;

    /**
     * Constructor vacío para permitir la creación de un objeto sin inicializar.
     * Los valores podrán ser configurados posteriormente mediante los setters.
     */
    public CentroCultivo() {
    }

    /**
     * Constructor principal que inicializa los datos fundamentales del centro.
     * Aplica validaciones mínimas para mantener consistencia,
     * siguiendo las recomendaciones recibidas durante la asignatura.
     *
     * @param nombreCentro nombre del centro; no debe estar vacío
     * @param comuna       comuna donde se ubica; no debe estar vacía
     * @param produccion   cantidad de producción; no puede ser negativa
     * @param producto     producto asociado al centro; no puede ser nulo
     * @throws IllegalArgumentException cuando alguno de los datos no es válido
     */
    public CentroCultivo(String nombreCentro, String comuna,Producto producto ,int produccion) {
        setNombreCentro(nombreCentro);
        setComuna(comuna);
        setProducto(producto);
        setProduccion(produccion);
    }

    /**
     * Getter y Setter de los atributos del centro de cultivo.
     */
    public String getNombreCentro() {
        return nombreCentro;
    }
    public void setNombreCentro(String nombreCentro) {
        if (nombreCentro == null || nombreCentro.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del centro no puede estar vacío.");
        }
        this.nombreCentro = nombreCentro.trim();
    }
    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("La comuna no puede estar vacía.");
        }
        this.comuna = comuna.trim();
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto asociado no puede ser nulo.");
        }
        this.producto = producto;
    }
    public int getProduccion() {
        return produccion;
    }
    public void setProduccion(int produccion) {
        if (produccion < 0) {
            throw new IllegalArgumentException("La producción no puede ser negativa.");
        }
        this.produccion = produccion;
    }


    /**
     * Entrega una representación en texto del centro de cultivo,
     * incluyendo sus atributos y el producto asociado.
     *
     * @return cadena con los datos estructurados del centro
     */
    @Override
    public String toString() {
        return "CentroCultivo [nombreCentro=" + nombreCentro +
                ", comuna=" + comuna +
                ", produccion=" + produccion +
                ", producto=" + producto + "]";
    }
}
