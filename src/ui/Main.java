package ui;

import data.GestorEntidades;
import model.interfaces.Registrable;
import model.ordenes.Producto;
import model.ordenes.OrdenDeCompra;
import model.persona.Cliente;
import model.persona.Colaborador;
import model.persona.Direccion;
import model.persona.Proveedor;
import model.persona.Rut;
import model.unidades.UnidadOperativa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Ventana principal de SalmonttApp.
 *
 * - Pantalla de inicio con bienvenida y opciones:
 *      • Cargar datos desde datos.txt
 *      • Ir a gestión de entidades (pestañas)
 *
 * - Pantalla principal con pestañas (JTabbedPane) para agregar y ver entidades.
 *
 * La impresión detallada en consola se realiza en formato tabla
 * y también se puede mostrar el detalle usando instanceof desde el GestorEntidades.
 *
 * Autor: Víctor Valenzuela
 */
public class Main extends JFrame {

    private GestorEntidades gestor;

    // CardLayout para manejar pantalla de inicio y pantalla de pestañas
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    private static final String PANTALLA_INICIO = "INICIO";
    private static final String PANTALLA_TABS = "TABS";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }

    public Main() {
        super("SalmonttApp");

        // Gestor inicial (lista vacía, se llena al cargar archivo o al agregar entidades)
        this.gestor = new GestorEntidades();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 520);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Panel de inicio (bienvenida) y panel con pestañas
        JPanel panelInicio = crearPanelInicio();
        JPanel panelPestanas = crearPanelPestanas();

        panelPrincipal.add(panelInicio, PANTALLA_INICIO);
        panelPrincipal.add(panelPestanas, PANTALLA_TABS);

        setContentPane(panelPrincipal);

        // Mostrar primero la pantalla de inicio
        cardLayout.show(panelPrincipal, PANTALLA_INICIO);
    }

    // ==========================
    //  PANTALLA DE INICIO
    // ==========================

    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Bienvenido a SalmonttApp");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel subtitulo = new JLabel("Sistema de gestión de unidades, personas, productos y órdenes de compra");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCargarDatos = new JButton("Cargar datos desde datos.txt");
        btnCargarDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCargarDatos.addActionListener(e -> cargarDatosDesdeArchivo());

        JButton btnIrAGestion = new JButton("Agregar / Gestionar datos");
        btnIrAGestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIrAGestion.addActionListener(e -> cardLayout.show(panelPrincipal, PANTALLA_TABS));

        panel.add(Box.createVerticalStrut(40));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnCargarDatos);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnIrAGestion);

        return panel;
    }

    /**
     * Reinstancia el GestorEntidades para recargar los datos desde el archivo datos.txt.
     */
    private void cargarDatosDesdeArchivo() {
        try {
            // Ruta relativa desde raíz del proyecto al ejecutar desde IntelliJ
            String ruta = "src/data/datos.txt";

            this.gestor = new GestorEntidades();
            this.gestor.cargarDesdeArchivo(ruta);

            JOptionPane.showMessageDialog(
                    this,
                    "Datos cargados correctamente desde:\n" + ruta +
                            "\nEntidades registradas: " + gestor.getEntidades().size(),
                    "Carga exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al cargar los datos: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================
    //  PANTALLA CON PESTAÑAS
    // ==========================

    private JPanel crearPanelPestanas() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Colaboradores", crearPanelColaboradores());
        pestañas.addTab("Clientes", crearPanelClientes());
        pestañas.addTab("Proveedores", crearPanelProveedores());
        pestañas.addTab("Productos", crearPanelProductos());
        pestañas.addTab("Compras", crearPanelCompras());
        pestañas.addTab("Resumen", crearPanelResumen());

        panel.add(pestañas, BorderLayout.CENTER);
        return panel;
    }

    // ==========================
    //  PANEL COLABORADORES
    // ==========================

    private JPanel crearPanelColaboradores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Gestión de Colaboradores");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar Colaborador");
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarColaborador());

        JButton btnVerResumen = new JButton("Ver Colaboradores (GUI)");
        btnVerResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerResumen.addActionListener(e -> mostrarResumenFiltrado(Colaborador.class, "Colaboradores"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerResumen);

        return panel;
    }

    // ==========================
    //  PANEL CLIENTES
    // ==========================

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Gestión de Clientes");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarCliente());

        JButton btnVerResumen = new JButton("Ver Clientes (GUI)");
        btnVerResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerResumen.addActionListener(e -> mostrarResumenFiltrado(Cliente.class, "Clientes"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerResumen);

        return panel;
    }

    // ==========================
    //  PANEL PROVEEDORES
    // ==========================

    private JPanel crearPanelProveedores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Gestión de Proveedores");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar Proveedor");
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarProveedor());

        JButton btnVerResumen = new JButton("Ver Proveedores (GUI)");
        btnVerResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerResumen.addActionListener(e -> mostrarResumenFiltrado(Proveedor.class, "Proveedores"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerResumen);

        return panel;
    }

    // ==========================
    //  PANEL PRODUCTOS
    // ==========================

    private JPanel crearPanelProductos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Gestión de Productos");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarProducto());

        JButton btnVerResumen = new JButton("Ver Productos (GUI)");
        btnVerResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerResumen.addActionListener(e -> mostrarResumenFiltrado(Producto.class, "Productos"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerResumen);

        return panel;
    }

    // ==========================
    //  PANEL COMPRAS
    // ==========================

    private JPanel crearPanelCompras() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Gestión de Compras (Órdenes de Compra)");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnVerResumen = new JButton("Ver Compras (GUI)");
        btnVerResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerResumen.addActionListener(e -> mostrarResumenFiltrado(OrdenDeCompra.class, "Compras"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnVerResumen);

        return panel;
    }

    // ==========================
    //  PANEL RESUMEN / CONSOLA
    // ==========================

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Resumen (GUI) y salida por consola");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnResumenGui = new JButton("Ver TODAS las entidades (GUI)");
        btnResumenGui.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnResumenGui.addActionListener(e -> mostrarResumenGeneralGUI());

        JButton btnUnidadesOrdenadas = new JButton("Ver Unidades ordenadas (GUI)");
        btnUnidadesOrdenadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUnidadesOrdenadas.addActionListener(e -> mostrarUnidadesOrdenadasGUI());

        JButton btnConsolaTabla = new JButton("Imprimir en consola (TABLA)");
        btnConsolaTabla.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConsolaTabla.addActionListener(e -> imprimirConsolaEnFormatoTabla());

        JButton btnConsolaInstanceof = new JButton("Detalle en consola (instanceof)");
        btnConsolaInstanceof.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConsolaInstanceof.addActionListener(e -> gestor.mostrarDetallesConInstanceof());

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnResumenGui);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnUnidadesOrdenadas);      // <-- nuevo botón
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnConsolaTabla);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnConsolaInstanceof);

        return panel;
    }

    // ==========================
    //  LÓGICA: AGREGAR ENTIDADES
    // ==========================

    private void agregarColaborador() {
        try {
            String nombre = pedirTexto("Nombre del colaborador:");
            if (nombre == null) return;

            String rutStr = pedirTexto("RUT del colaborador:");
            if (rutStr == null) return;

            String email = pedirTexto("Email del colaborador:");
            if (email == null) return;

            String calle = pedirTexto("Calle del domicilio:");
            if (calle == null) return;
            String numero = pedirTexto("Número:");
            if (numero == null) return;
            String comunaDir = pedirTexto("Comuna:");
            if (comunaDir == null) return;
            String region = pedirTexto("Región:");
            if (region == null) return;

            String nombreUnidad = pedirTexto("Nombre de la unidad operativa donde trabaja:");
            if (nombreUnidad == null) return;
            String comunaUnidad = pedirTexto("Comuna de la unidad operativa:");
            if (comunaUnidad == null) return;

            String area = pedirTexto("Área (Ej: Alimentación, Envasado):");
            if (area == null) return;

            String cargo = pedirTexto("Cargo (Ej: Operador, Supervisor):");
            if (cargo == null) return;

            UnidadOperativa unidad = new UnidadOperativa(nombreUnidad, comunaUnidad);

            Colaborador colaborador = new Colaborador(
                    nombre,
                    new Rut(rutStr),
                    email,
                    new Direccion(calle, numero, comunaDir, region),
                    unidad,
                    area,
                    cargo
            );

            gestor.agregarEntidad(colaborador);

            JOptionPane.showMessageDialog(this, "Colaborador agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCliente() {
        try {
            String idCliente = pedirTexto("ID del cliente (Ej: C001):");
            if (idCliente == null) return;

            String nombre = pedirTexto("Nombre del cliente:");
            if (nombre == null) return;

            String rutStr = pedirTexto("RUT del cliente:");
            if (rutStr == null) return;

            String email = pedirTexto("Email del cliente:");
            if (email == null) return;

            String calle = pedirTexto("Calle del domicilio:");
            if (calle == null) return;
            String numero = pedirTexto("Número:");
            if (numero == null) return;
            String comunaDir = pedirTexto("Comuna:");
            if (comunaDir == null) return;
            String region = pedirTexto("Región:");
            if (region == null) return;

            String[] opcionesPago = {"Efectivo", "Tarjeta", "Transferencia"};
            String tipoPago = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione método de pago:",
                    "Método de pago",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesPago,
                    opcionesPago[0]
            );
            if (tipoPago == null) return;

            model.interfaces.MetodoPago metodoPago;
            switch (tipoPago) {
                case "Tarjeta" -> metodoPago = new model.pagos.Tarjeta();
                case "Transferencia" -> metodoPago = new model.pagos.Transferencia();
                default -> metodoPago = new model.pagos.Efectivo();
            }

            Cliente cliente = new Cliente(
                    nombre,
                    new Rut(rutStr),
                    email,
                    new Direccion(calle, numero, comunaDir, region),
                    idCliente,
                    metodoPago
            );

            gestor.agregarEntidad(cliente);

            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al agregar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarProveedor() {
        try {
            String nombreEmpresa = pedirTexto("Nombre de la empresa proveedora:");
            if (nombreEmpresa == null) return;

            String rubro = pedirTexto("Rubro del proveedor:");
            if (rubro == null) return;

            String contactoPrincipal = pedirTexto("Nombre de la persona de contacto:");
            if (contactoPrincipal == null) return;

            String telefono = pedirTexto("Teléfono de contacto:");
            if (telefono == null) return;

            Proveedor proveedor = new Proveedor(nombreEmpresa, rubro, contactoPrincipal, telefono);
            gestor.agregarEntidad(proveedor);

            JOptionPane.showMessageDialog(this, "Proveedor agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarProducto() {
        try {
            String nombreProducto = pedirTexto("Nombre del producto:");
            if (nombreProducto == null) return;

            String comuna = pedirTexto("Comuna asociada al producto:");
            if (comuna == null) return;

            String tipoProducto = pedirTexto("Tipo de producto (Ej: Pez vivo, Filete, etc):");
            if (tipoProducto == null) return;

            String cantidadStr = pedirTexto("Cantidad (número):");
            if (cantidadStr == null) return;
            double cantidad = Double.parseDouble(cantidadStr);

            String nombreUnidadOrigen = pedirTexto("Unidad de origen (nombre centro/planta):");
            if (nombreUnidadOrigen == null) return;

            UnidadOperativa unidadOrigen = new UnidadOperativa(nombreUnidadOrigen, comuna);

            Producto producto = new Producto(
                    nombreProducto,
                    comuna,
                    tipoProducto,
                    cantidad,
                    unidadOrigen
            );

            gestor.agregarEntidad(producto);

            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ==========================
    //  RESÚMENES EN GUI (TABLA)
    // ==========================

    private void mostrarResumenGeneralGUI() {
        List<Registrable> entidades = gestor.getEntidades();
        if (entidades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay entidades registradas.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s | %-20s | %-30s%n", "Tipo", "Identificador", "Detalle"));
        sb.append("--------------------------------------------------------------------------\n");

        for (Registrable r : entidades) {
            String tipo = r.getClass().getSimpleName();
            String identificador;
            String detalle;

            if (r instanceof Colaborador colaborador) {
                identificador = colaborador.getNombre();
                detalle = colaborador.getCargo() + " - " + colaborador.getArea();
            } else if (r instanceof Cliente cliente) {
                identificador = cliente.getIdCliente();
                detalle = cliente.getNombre() + " (" + cliente.getRut() + ")";
            } else if (r instanceof Proveedor proveedor) {
                identificador = proveedor.getNombreEmpresa();
                detalle = proveedor.getRubro();
            } else if (r instanceof Producto producto) {
                identificador = producto.getNombre();
                detalle = producto.getTipoProducto() + " (" + producto.getCantidad() + ")";
            } else if (r instanceof OrdenDeCompra oc) {
                identificador = oc.getIdOrden();
                detalle = oc.getCliente().getNombre() + " - " + oc.getProducto().getNombre();
            } else if (r instanceof UnidadOperativa uo) {
                identificador = uo.getNombre();
                detalle = "Comuna: " + uo.getComuna();
            } else {
                identificador = tipo;
                detalle = r.toString();
            }

            sb.append(String.format("%-15s | %-20s | %-30s%n",
                    tipo,
                    recortar(identificador, 20),
                    recortar(detalle, 30)
            ));
        }

        mostrarTextoEnVentana(sb.toString(), "Resumen general de entidades");
    }

    /**
     * Nueva vista GUI para mostrar solo unidades operativas ordenadas
     * usando Collections.sort() aplicado en GestorEntidades.getUnidadesOrdenadas().
     */
    private void mostrarUnidadesOrdenadasGUI() {
        List<UnidadOperativa> unidades = gestor.getUnidadesOrdenadas();

        if (unidades.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay unidades operativas registradas.",
                    "Sin datos",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Unidades operativas ordenadas por nombre (Collections.sort)\n");
        sb.append(String.format("%-18s | %-12s |%n", "Nombre", "Comuna"));
        sb.append("-------------------------------------------\n");

        for (UnidadOperativa uo : unidades) {
            sb.append(String.format("%-18s | %-12s |%n",
                    uo.getNombre(),
                    uo.getComuna()
            ));
        }

        mostrarTextoEnVentana(sb.toString(), "Unidades operativas ordenadas");
    }

    private void mostrarResumenFiltrado(Class<?> tipoFiltro, String titulo) {
        List<Registrable> entidades = gestor.getEntidades();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s | %-20s | %-30s%n", "Tipo", "Identificador", "Detalle"));
        sb.append("--------------------------------------------------------------------------\n");

        boolean hayDatos = false;

        for (Registrable r : entidades) {
            if (!tipoFiltro.isInstance(r)) continue;

            hayDatos = true;
            String tipo = r.getClass().getSimpleName();
            String identificador;
            String detalle;

            if (r instanceof Colaborador colaborador) {
                identificador = colaborador.getNombre();
                detalle = colaborador.getCargo() + " - " + colaborador.getArea();
            } else if (r instanceof Cliente cliente) {
                identificador = cliente.getIdCliente();
                detalle = cliente.getNombre() + " (" + cliente.getRut() + ")";
            } else if (r instanceof Proveedor proveedor) {
                identificador = proveedor.getNombreEmpresa();
                detalle = proveedor.getRubro();
            } else if (r instanceof Producto producto) {
                identificador = producto.getNombre();
                detalle = producto.getTipoProducto() + " (" + producto.getCantidad() + ")";
            } else if (r instanceof OrdenDeCompra oc) {
                identificador = oc.getIdOrden();
                detalle = oc.getCliente().getNombre() + " - " + oc.getProducto().getNombre();
            } else if (r instanceof UnidadOperativa uo) {
                identificador = uo.getNombre();
                detalle = "Comuna: " + uo.getComuna();
            } else {
                identificador = tipo;
                detalle = r.toString();
            }

            sb.append(String.format("%-15s | %-20s | %-30s%n",
                    tipo,
                    recortar(identificador, 20),
                    recortar(detalle, 30)
            ));
        }

        if (!hayDatos) {
            JOptionPane.showMessageDialog(this, "No hay entidades registradas del tipo: " + titulo, "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        mostrarTextoEnVentana(sb.toString(), "Resumen de " + titulo);
    }

    private void mostrarTextoEnVentana(String texto, String titulo) {
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(740, 320));

        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private String recortar(String texto, int largoMax) {
        if (texto == null) return "";
        return texto.length() <= largoMax ? texto : texto.substring(0, largoMax - 3) + "...";
    }

    private String pedirTexto(String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje, "SalmonttApp", JOptionPane.QUESTION_MESSAGE);
    }

    // ==========================
    //  CONSOLA: FORMATO TABLA
    // ==========================

    private void imprimirConsolaEnFormatoTabla() {

        List<Registrable> entidades = gestor.getEntidades();

        System.out.println("\n==================== SALMONTTAPP ====================");
        System.out.println("                LISTADO EN TABLAS");
        System.out.println("=====================================================\n");

        // Unidades Operativas (usando lista ORDENADA)
        System.out.println("------------- UNIDADES OPERATIVAS -------------------");
        System.out.printf("%-18s | %-12s | %-15s |%n", "Nombre", "Comuna", "Dato");
        System.out.println("-----------------------------------------------------");
        for (UnidadOperativa uo : gestor.getUnidadesOrdenadas()) {
            uo.mostrarDatos();   // usa toString() tabular de la unidad
        }

        // Colaboradores
        System.out.println("\n----------------- COLABORADORES ---------------------");
        System.out.printf("%-18s | %-12s | %-20s | %-15s | %-15s |%n",
                "Nombre", "RUT", "Cargo", "Area", "Unidad");
        System.out.println("--------------------------------------------------------------------------");
        for (Registrable r : entidades) {
            if (r instanceof Colaborador) {
                r.mostrarDatos();
            }
        }

        // Clientes
        System.out.println("\n------------------- CLIENTES ------------------------");
        System.out.printf("%-12s | %-18s | %-12s | %-25s | %-15s |%n",
                "ID Cliente", "Nombre", "RUT", "Email", "Método Pago");
        System.out.println("--------------------------------------------------------------------------------");
        for (Registrable r : entidades) {
            if (r instanceof Cliente) {
                r.mostrarDatos();
            }
        }

        // Proveedores
        System.out.println("\n------------------ PROVEEDORES ----------------------");
        System.out.printf("%-22s | %-15s | %-18s | %-14s |%n",
                "Empresa", "Rubro", "Contacto", "Telefono");
        System.out.println("---------------------------------------------------------------------------");
        for (Registrable r : entidades) {
            if (r instanceof Proveedor) {
                r.mostrarDatos();
            }
        }

        // Productos
        System.out.println("\n------------------- PRODUCTOS -----------------------");
        System.out.printf("%-18s | %-12s | %-20s | %-8s | %-15s |%n",
                "Nombre", "Comuna", "Tipo", "Cant.", "Unidad origen");
        System.out.println("---------------------------------------------------------------------------");
        for (Registrable r : entidades) {
            if (r instanceof Producto) {
                r.mostrarDatos();
            }
        }

        // Compras
        System.out.println("\n------------------- COMPRAS -------------------------");
        System.out.println("(Depende del toString() implementado en OrdenDeCompra)");
        System.out.println("-----------------------------------------------------");
        for (Registrable r : entidades) {
            if (r instanceof OrdenDeCompra) {
                r.mostrarDatos();
            }
        }

        System.out.println("\n=====================================================");
    }
}
