package model;

/**
 * Representa un centro de cultivo perteneciente a la empresa.
 *
 * <p>
 * Esta clase extiende a {@link UnidadOperativa}, heredando atributos
 * comunes como nombre y comuna. Además, incorpora información específica
 * de un centro de cultivo, como sus toneladas de producción.
 * </p>
 *
 * <p>
 * Su propósito es modelar de forma precisa una unidad operativa dedicada
 * al cultivo, permitiendo diferenciarla de otras unidades como plantas
 * de proceso en la jerarquía del sistema.
 * </p>
 */
public class CentroCultivo extends UnidadOperativa {

    private int toneladasProduccion;

    /**
     * Construye un nuevo CentroCultivo utilizando su nombre, comuna
     * y nivel de producción expresado en toneladas.
     *
     * @param nombre nombre del centro de cultivo
     * @param comuna comuna donde se ubica el centro
     * @param toneladasProduccion producción anual del centro en toneladas
     */
    public CentroCultivo(String nombre, String comuna, int toneladasProduccion) {
        super(nombre, comuna);
        this.toneladasProduccion = toneladasProduccion;
    }

    /**
     * Obtiene la producción anual del centro de cultivo en toneladas.
     *
     * @return producción anual en toneladas
     */
    public int getToneladasProduccion() {
        return toneladasProduccion;
    }

    /**
     * Muestra por consola la información específica del centro de cultivo.
     * Este método sobrescribe el comportamiento base de {@link UnidadOperativa}
     * para incluir el dato de producción anual.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println(
                "Centro de Cultivo: " + nombre +
                        ", Comuna: " + comuna +
                        ", Producción: " + toneladasProduccion + " toneladas"
        );
    }

    /**
     * Retorna una representación formateada del centro de cultivo.
     * Este método muestra la información en formato de tabla y aplica
     * color azul mediante códigos ANSI para destacar visualmente
     * este tipo de unidad operativa.
     *
     * @return cadena formateada del centro de cultivo
     */
    @Override
    public String toString() {
        return String.format(
                "\u001B[34m%-18s | %-10s | %-13s |\u001B[0m",
                nombre,
                comuna,
                toneladasProduccion + " t"
        );
    }
}
