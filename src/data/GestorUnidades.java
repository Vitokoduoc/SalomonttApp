package data;

import model.CentroCultivo;
import model.PlantaProceso;
import model.UnidadOperativa;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de generar instancias de prueba para las distintas
 * unidades operativas del sistema.
 *
 * <p>
 * Esta clase actúa como un generador de datos iniciales, creando objetos
 * de {@link CentroCultivo} y {@link PlantaProceso} para ser utilizados
 * por la interfaz principal o para pruebas internas del sistema.
 * </p>
 *
 * <p>
 * Su propósito es desacoplar la creación de objetos del flujo principal,
 * manteniendo una organización clara entre el modelo de datos y la lógica
 * de inicialización.
 * </p>
 *
 * <p><b>Autor:</b> Víctor Valenzuela</p>
 */
public class GestorUnidades {

    /**
     * Crea y devuelve una lista que contiene instancias de unidades operativas,
     * incluyendo centros de cultivo y plantas de proceso.
     *
     * <p>
     * La colección se declara utilizando el tipo genérico {@code List<UnidadOperativa>},
     * permitiendo almacenar distintos tipos de subclases en una misma estructura
     * y recorrerlas de forma polimórfica.
     * </p>
     *
     * @return lista de unidades operativas de prueba
     */
    public List<UnidadOperativa> crearUnidades() {
        List<UnidadOperativa> unidades = new ArrayList<>();

        // Crear tres centros de cultivo
        unidades.add(new CentroCultivo("Calbuco Norte", "Calbuco", 1200));
        unidades.add(new CentroCultivo("Isla Huar", "Calbuco", 1100));
        unidades.add(new CentroCultivo("Chacao Norte", "Ancud", 980));

        // Crear dos plantas de proceso
        unidades.add(new PlantaProceso("Planta Ancud", "Ancud", 500));
        unidades.add(new PlantaProceso("Planta Quellón", "Quellón", 850));

        return unidades;
    }
}
