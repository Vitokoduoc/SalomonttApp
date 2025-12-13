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
 * Implementa la interfaz {@link Registrable} para permitir su gestión
 * polimórfica dentro de colecciones dinámicas que integran distintas
 * entidades del sistema.
 * </p>
 */
public class CentroCultivo extends UnidadOperativa implements Registrable {

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
     * Sobrescribe el comportamiento base de {@link UnidadOperativa}.
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
     * Muestra un resumen de la entidad, utilizado en el contexto de la
     * interfaz Registrable y la colección polimórfica.
     */
    @Override
    public void mostrarResumen() {
        System.out.println(
                "[Resumen Centro de Cultivo] " + nombre +
                        " | Producción: " + toneladasProduccion + " t"
        );
    }

    /**
     * Retorna una representación formateada del centro de cultivo.
     * Incluye formato visual mediante códigos ANSI.
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
