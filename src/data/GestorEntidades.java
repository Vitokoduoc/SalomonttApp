package data;

import model.*;

import java.util.ArrayList;

/**
 * Gestiona una colección polimórfica de entidades de la empresa SalmonttApp.
 *
 * <p>
 * Todas las entidades implementan la interfaz {@link Registrable}, lo que
 * permite almacenarlas en una misma colección y diferenciarlas mediante
 * instanceof durante la ejecución.
 * </p>
 */
public class GestorEntidades {

    private ArrayList<Registrable> entidades;

    /**
     * Constructor: inicializa la colección dinámica.
     */
    public GestorEntidades() {
        entidades = new ArrayList<>();
        cargarDatosIniciales();
    }

    /**
     * Crea datos de ejemplo para probar el sistema.
     */
    private void cargarDatosIniciales() {

        // Unidades operativas
        UnidadOperativa cc1 = new CentroCultivo("Calbuco Norte", "Calbuco", 1200);
        UnidadOperativa cc2 = new CentroCultivo("Isla Huar", "Calbuco", 1100);
        UnidadOperativa pp1 = new PlantaProceso("Planta Ancud", "Ancud", 500);

        // Colaborador asignado a una unidad operativa
        Colaborador col1 = new Colaborador(
                "Juan Pérez",
                "12.345.678-9",
                "jperez@empresa.cl",
                "Av. Los Ríos 123",
                cc1,
                "Alimentación",
                "Operador"
        );




        // Proveedor
        Proveedor prov1 = new Proveedor(
                "Maersk Logistics",
                "Transporte Marítimo",
                "Carlos Soto",
                "+56 9 7711 3344"
        );

        // Producto asociado a unidad operativa
        Producto prod1 = new Producto(
                "Salmón Atlántico",
                "Calbuco",
                "Pez vivo",
                3200,
                cc2
        );

        // Agregar todo a la lista polimórfica
        entidades.add(cc1);
        entidades.add(cc2);
        entidades.add(pp1);
        entidades.add(col1);
        entidades.add(prov1);
        entidades.add(prod1);
    }

    /**
     * Devuelve la lista polimórfica de entidades.
     */
    public ArrayList<Registrable> getEntidades() {
        return entidades;
    }

    /**
     * Recorre la colección y aplica lógica diferenciada según tipo.
     */
    public void mostrarDetallesConInstanceof() {
        System.out.println("=== Detalle de entidades ===");

        for (Registrable r : entidades) {

            if (r instanceof CentroCultivo) {
                System.out.println("[Centro de Cultivo detectado]");
                r.mostrarResumen();
            }
            else if (r instanceof PlantaProceso) {
                System.out.println("[Planta de Proceso detectada]");
                r.mostrarResumen();
            }
            else if (r instanceof Colaborador) {
                System.out.println("[Colaborador detectado]");
                r.mostrarResumen();
            }
            else if (r instanceof Proveedor) {
                System.out.println("[Proveedor detectado]");
                r.mostrarResumen();
            }
            else if (r instanceof Producto) {
                System.out.println("[Producto detectado]");
                r.mostrarResumen();
            }
            else {
                System.out.println("[Entidad desconocida]");
                r.mostrarResumen();
            }
        }
    }
}
