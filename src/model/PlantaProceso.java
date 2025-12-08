package model;

/**
 * Representa una planta de proceso dentro de la empresa.
 *
 * <p>
 * Esta clase extiende a {@link UnidadOperativa}, heredando atributos
 * comunes como nombre y comuna. Modela unidades dedicadas al procesamiento
 * de productos, incorporando características específicas como su capacidad
 * de operación diaria.
 * </p>
 *
 * <p>
 * Su función es diferenciar este tipo de unidad dentro de la jerarquía
 * del sistema, permitiendo representar operaciones industriales que
 * procesan la producción obtenida desde los centros de cultivo.
 * </p>
 */
public class PlantaProceso extends UnidadOperativa {

    private int capacidadProceso;

    /**
     * Construye una nueva PlantaProceso con nombre, comuna y capacidad
     * de procesamiento medida en toneladas por día.
     *
     * @param nombre nombre de la planta de proceso
     * @param comuna comuna donde está ubicada la planta
     * @param capacidadProceso capacidad diaria de procesamiento (t/día)
     */
    public PlantaProceso(String nombre, String comuna, int capacidadProceso) {
        super(nombre, comuna);
        this.capacidadProceso = capacidadProceso;
    }

    /**
     * Obtiene la capacidad diaria de procesamiento de la planta.
     *
     * @return capacidad de procesamiento en toneladas por día
     */
    public int getCapacidad() {
        return capacidadProceso;
    }

    /**
     * Muestra por consola la información específica de la planta de proceso.
     * Sobrescribe el comportamiento base de {@link UnidadOperativa} para
     * incluir la capacidad diaria de procesamiento.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println(
                "Planta de Proceso: " + nombre +
                        ", Comuna: " + comuna +
                        ", Capacidad: " + capacidadProceso + " t por día"
        );
    }

    /**
     * Retorna una representación formateada de la planta de proceso.
     * La información se muestra en formato de tabla y utiliza color verde
     * mediante códigos ANSI para resaltar visualmente este tipo de unidad.
     *
     * @return cadena formateada con los datos de la planta de proceso
     */
    @Override
    public String toString() {
        return String.format(
                "\u001B[32m%-18s | %-10s | %-10s |\u001B[0m",
                nombre,
                comuna,
                capacidadProceso + " t por día"
        );
    }
}
