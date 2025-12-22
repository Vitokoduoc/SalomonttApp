package data;

import model.interfaces.Registrable;
import model.interfaces.MetodoPago;
import model.ordenes.Producto;
import model.ordenes.OrdenDeCompra;
import model.persona.Cliente;
import model.persona.Colaborador;
import model.persona.Direccion;
import model.persona.Proveedor;
import model.persona.Rut;
import model.unidades.CentroCultivo;
import model.unidades.PlantaProceso;
import model.unidades.UnidadOperativa;
import util.LectorArchivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestiona una colección polimórfica de entidades de la empresa SalmonttApp.
 *
 * Autor: Víctor Valenzuela
 */
public class GestorEntidades {

    private final List<Registrable> entidades;

    /**
     * Crea un gestor con la lista vacía.
     * Los datos se cargan explícitamente con cargarDesdeArchivo().
     */
    public GestorEntidades() {
        this.entidades = new ArrayList<>();
    }

    /**
     * Carga todas las entidades desde un archivo de texto.
     * El formato esperado es el que definiste en data/datos.txt.
     *
     * @param ruta ruta del archivo (por ejemplo "src/data/datos.txt")
     */
    public void cargarDesdeArchivo(String ruta) {
        entidades.clear();

        List<String> lineas = LectorArchivo.leerArchivo(ruta);

        for (String linea : lineas) {
            if (linea.trim().isEmpty() || linea.trim().startsWith("#")) {
                continue;
            }

            String[] partes = linea.split(";");
            if (partes.length == 0) continue;

            String tipo = partes[0].trim().toUpperCase();

            try {
                switch (tipo) {
                    case "CENTRO" -> cargarCentroCultivo(partes);
                    case "PLANTA" -> cargarPlantaProceso(partes);
                    case "PROVEEDOR" -> cargarProveedor(partes);
                    case "COLABORADOR" -> cargarColaborador(partes);
                    case "CLIENTE" -> cargarCliente(partes);
                    case "PRODUCTO" -> cargarProducto(partes);
                    case "COMPRA" -> cargarCompra(partes);
                    default -> System.out.println("Tipo no reconocido en línea: " + linea);
                }
            } catch (Exception e) {
                System.out.println("Error procesando línea: " + linea + " -> " + e.getMessage());
            }
        }
    }

    // ==========================
    //  HELPERS DE CARGA
    // ==========================

    private void cargarCentroCultivo(String[] partes) {
        // CENTRO;Calbuco Norte;Calbuco;1200
        if (partes.length < 4) return;
        String nombre = partes[1];
        String comuna = partes[2];
        int toneladas = Integer.parseInt(partes[3]);
        CentroCultivo cc = new CentroCultivo(nombre, comuna, toneladas);
        agregarEntidad(cc);
    }

    private void cargarPlantaProceso(String[] partes) {
        // PLANTA;Planta Ancud;Ancud;500
        if (partes.length < 4) return;
        String nombre = partes[1];
        String comuna = partes[2];
        int capacidad = Integer.parseInt(partes[3]);
        PlantaProceso pp = new PlantaProceso(nombre, comuna, capacidad);
        agregarEntidad(pp);
    }

    private void cargarProveedor(String[] partes) {
        // PROVEEDOR;Maersk Logistics;Transporte Marítimo;Carlos Soto;+569...
        if (partes.length < 5) return;
        String nombreEmpresa = partes[1];
        String rubro = partes[2];
        String contacto = partes[3];
        String telefono = partes[4];
        Proveedor proveedor = new Proveedor(nombreEmpresa, rubro, contacto, telefono);
        agregarEntidad(proveedor);
    }

    private void cargarColaborador(String[] partes) {
        // COLABORADOR;nombre;rut;email;calle;numero;comunaDir;region;unidadNombre;unidadComuna;area;cargo
        if (partes.length < 12) return;

        String nombre = partes[1];
        String rutStr = partes[2];
        String email = partes[3];
        String calle = partes[4];
        String numero = partes[5];
        String comunaDir = partes[6];
        String region = partes[7];
        String unidadNombre = partes[8];
        String unidadComuna = partes[9];
        String area = partes[10];
        String cargo = partes[11];

        Direccion direccion = new Direccion(calle, numero, comunaDir, region);
        Rut rut = new Rut(rutStr);
        UnidadOperativa unidad = new UnidadOperativa(unidadNombre, unidadComuna);

        Colaborador colaborador = new Colaborador(
                nombre,
                rut,
                email,
                direccion,
                unidad,
                area,
                cargo
        );
        agregarEntidad(colaborador);
    }

    private void cargarCliente(String[] partes) {
        // CLIENTE;idCliente;nombre;rut;email;calle;numero;comuna;region;metodoPago
        if (partes.length < 10) return;

        String idCliente = partes[1];
        String nombre = partes[2];
        String rutStr = partes[3];
        String email = partes[4];
        String calle = partes[5];
        String numero = partes[6];
        String comuna = partes[7];
        String region = partes[8];
        String metodoPagoCodigo = partes[9];

        Direccion direccion = new Direccion(calle, numero, comuna, region);
        Rut rut = new Rut(rutStr);
        MetodoPago metodoPago = crearMetodoPagoDesdeCodigo(metodoPagoCodigo);

        Cliente cliente = new Cliente(
                nombre,
                rut,
                email,
                direccion,
                idCliente,
                metodoPago
        );
        agregarEntidad(cliente);
    }

    private void cargarProducto(String[] partes) {
        // PRODUCTO;nombre;comuna;tipoProducto;cantidad;unidadOrigenNombre;unidadOrigenComuna
        if (partes.length < 7) return;

        String nombre = partes[1];
        String comuna = partes[2];
        String tipoProducto = partes[3];
        double cantidad = Double.parseDouble(partes[4]);
        String unidadNombre = partes[5];
        String unidadComuna = partes[6];

        UnidadOperativa unidadOrigen = new UnidadOperativa(unidadNombre, unidadComuna);

        Producto producto = new Producto(
                nombre,
                comuna,
                tipoProducto,
                cantidad,
                unidadOrigen
        );
        agregarEntidad(producto);
    }

    private void cargarCompra(String[] partes) {
        // COMPRA;idOrden;idCliente;nombreProducto;cantidad;metodoPago;fecha(YYYY-MM-DD)
        if (partes.length < 7) return;

        String idOrden = partes[1];
        String idCliente = partes[2];
        String nombreProducto = partes[3];
        int cantidad = Integer.parseInt(partes[4]);
        String metodoPagoCodigo = partes[5];
        String fechaStr = partes[6];

        Cliente cliente = buscarClientePorId(idCliente);
        Producto producto = buscarProductoPorNombre(nombreProducto);

        if (cliente == null || producto == null) {
            System.out.println("No se encontró cliente o producto para la compra " + idOrden);
            return;
        }

        MetodoPago metodoPago = crearMetodoPagoDesdeCodigo(metodoPagoCodigo);
        LocalDate fecha = LocalDate.parse(fechaStr);

        OrdenDeCompra orden = new OrdenDeCompra(
                idOrden,
                cliente,
                producto,
                cantidad,
                metodoPago,
                fecha
        );
        agregarEntidad(orden);
    }

    private MetodoPago crearMetodoPagoDesdeCodigo(String codigo) {
        if (codigo == null) return new model.pagos.Efectivo();

        String c = codigo.trim().toUpperCase();
        return switch (c) {
            case "TARJETA" -> new model.pagos.Tarjeta();
            case "TRANSFERENCIA" -> new model.pagos.Transferencia();
            default -> new model.pagos.Efectivo();
        };
    }

    private Cliente buscarClientePorId(String idCliente) {
        for (Registrable r : entidades) {
            if (r instanceof Cliente c) {
                if (c.getIdCliente().equalsIgnoreCase(idCliente)) {
                    return c;
                }
            }
        }
        return null;
    }

    private Producto buscarProductoPorNombre(String nombreProducto) {
        for (Registrable r : entidades) {
            if (r instanceof Producto p) {
                if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                    return p;
                }
            }
        }
        return null;
    }

    // ==========================
    //  API PÚBLICA
    // ==========================

    public List<Registrable> getEntidades() {
        return entidades;
    }

    public void agregarEntidad(Registrable entidad) {
        if (entidad != null) {
            entidades.add(entidad);
            // Uso simbólico de registrar() para cumplir con el enunciado
            entidad.registrar();
        }
    }

    /**
     * Devuelve una lista NUEVA con todas las unidades operativas
     * ordenadas alfabéticamente por nombre, usando compareTo().
     */
    public List<UnidadOperativa> getUnidadesOrdenadas() {
        List<UnidadOperativa> unidades = new ArrayList<>();

        for (Registrable r : entidades) {
            if (r instanceof UnidadOperativa uo) {
                unidades.add(uo);
            }
        }

        // Recomendación del profesor: usar Collections.sort()
        Collections.sort(unidades);

        return unidades;
    }

    /**
     * Recorre la colección y aplica lógica diferenciada según tipo usando instanceof.
     */
    public void mostrarDetallesConInstanceof() {
        System.out.println("=================================");
        System.out.println("     DETALLE DE ENTIDADES");
        System.out.println("=================================");

        for (Registrable r : entidades) {

            if (r instanceof CentroCultivo) {
                System.out.println("[Centro de Cultivo]");
            } else if (r instanceof PlantaProceso) {
                System.out.println("[Planta de Proceso]");
            } else if (r instanceof Colaborador) {
                System.out.println("[Colaborador]");
            } else if (r instanceof Cliente) {
                System.out.println("[Cliente]");
            } else if (r instanceof Proveedor) {
                System.out.println("[Proveedor]");
            } else if (r instanceof Producto) {
                System.out.println("[Producto]");
            } else if (r instanceof OrdenDeCompra) {
                System.out.println("[Orden de Compra]");
            } else {
                System.out.println("[Entidad desconocida]");
            }

            r.mostrarDatos();
        }
    }
}
