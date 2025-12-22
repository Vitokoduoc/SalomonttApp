package data;

import model.unidades.CentroCultivo;
import model.unidades.PlantaProceso;
import model.unidades.UnidadOperativa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase encargada de generar instancias de prueba para las distintas
 * unidades operativas del sistema.
 *
 * Autor: Víctor Valenzuela
 */
public class GestorUnidades {

    /**
     * Crea y devuelve una lista ordenada de unidades operativas.
     *
     * @return lista ordenada de unidades operativas de prueba
     */
    public List<UnidadOperativa> crearUnidades() {

        List<UnidadOperativa> unidades = new ArrayList<>();

        // Centros de cultivo
        unidades.add(new CentroCultivo("Calbuco Norte", "Calbuco", 1200));
        unidades.add(new CentroCultivo("Isla Huar", "Calbuco", 1100));
        unidades.add(new CentroCultivo("Chacao Norte", "Ancud", 980));

        // Plantas de proceso
        unidades.add(new PlantaProceso("Planta Ancud", "Ancud", 500));
        unidades.add(new PlantaProceso("Planta Quellón", "Quellón", 850));

        // 🔹 Ordenamiento automático por nombre (Comparable)
        Collections.sort(unidades);

        return unidades;
    }
}
