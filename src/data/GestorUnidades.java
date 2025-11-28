package data;

import model.CentroCultivo;
import model.PlantaProceso;
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
     * Se generan cuatro objetos como datos de prueba: dos centros de cultivo y
     * dos plantas de proceso. Esta información permite validar el funcionamiento
     * de la jerarquía de clases y el método {@code toString()} sobrescrito en
     * cada subclase.
     * </p>
     *
     * @return lista de objetos que representan unidades operativas
     */
    public List<Object> crearUnidades() {
        List<Object> unidades = new ArrayList<>();

        // Crear dos centros de cultivo
        unidades.add(new CentroCultivo("Isla Huar", "Calbuco", 1200));
        unidades.add(new CentroCultivo("Chacao Norte", "Ancud", 980));

        // Crear dos plantas de proceso
        unidades.add(new PlantaProceso("Planta Ancud", "Ancud", 500));
        unidades.add(new PlantaProceso("Planta Quellón", "Quellón", 850));

        return unidades;
    }
}
