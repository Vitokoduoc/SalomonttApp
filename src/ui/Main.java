package ui;

import data.GestorEntidades;
import model.Colaborador;
import model.Producto;
import model.Proveedor;
import model.Registrable;
import model.UnidadOperativa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana principal de SalmonttApp.
 *
 * Muestra una interfaz gráfica con pestañas (JTabbedPane) para gestionar
 * las distintas entidades que implementan la interfaz Registrable.
 *
 * La impresión detallada en consola se sigue realizando desde
 * GestorEntidades.mostrarDetallesConInstanceof(), en formato de texto
 * como se venía trabajando.
 */
public class Main extends JFrame {

    private final GestorEntidades gestor;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }

    public Main() {
        super("SalmonttApp");

        this.gestor = new GestorEntidades();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Colaboradores", crearPanelColaboradores());
        pestañas.addTab("Proveedores", crearPanelProveedores());
        pestañas.addTab("Productos", crearPanelProductos());
        pestañas.addTab("Resumen", crearPanelResumen());

        add(pestañas, BorderLayout.CENTER);
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

        JButton btnVerResumen = new JButton("Ver Colaboradores");
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

        JButton btnVerResumen = new JButton("Ver Proveedores");
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

        JButton btnVerResumen = new JButton("Ver Productos");
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
    //  PANEL RESUMEN / CONSOLA
    // ==========================

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("Resumen general y salida por consola");
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnResumenGui = new JButton("Ver TODAS las entidades");
        btnResumenGui.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnResumenGui.addActionListener(e -> mostrarResumenGeneralGUI());

        JButton btnConsola = new JButton("Mostrar detalle en consola (instanceof)");
        btnConsola.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConsola.addActionListener(e -> gestor.mostrarDetallesConInstanceof());

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnResumenGui);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnConsola);

        return panel;
    }

    // ==========================
    //  LÓGICA: AGREGAR ENTIDADES
    // ==========================

    private void agregarColaborador() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del colaborador:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null) return;

            String rut = JOptionPane.showInputDialog(this, "RUT del colaborador:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (rut == null) return;

            String email = JOptionPane.showInputDialog(this, "Email del colaborador:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (email == null) return;

            String direccion = JOptionPane.showInputDialog(this, "Dirección del colaborador:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (direccion == null) return;

            String nombreUnidad = JOptionPane.showInputDialog(this, "Nombre de la unidad operativa donde trabaja:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (nombreUnidad == null) return;

            String comunaUnidad = JOptionPane.showInputDialog(this, "Comuna de la unidad operativa:", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (comunaUnidad == null) return;

            String area = JOptionPane.showInputDialog(this, "Área (Ej: Alimentación, Envasado):", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (area == null) return;

            String cargo = JOptionPane.showInputDialog(this, "Cargo (Ej: Operador, Supervisor):", "Nuevo Colaborador", JOptionPane.QUESTION_MESSAGE);
            if (cargo == null) return;

            UnidadOperativa unidad = new UnidadOperativa(nombreUnidad, comunaUnidad);

            Colaborador colaborador = new Colaborador(
                    nombre,
                    rut,
                    email,
                    direccion,
                    unidad,
                    area,
                    cargo
            );

            gestor.getEntidades().add(colaborador);

            JOptionPane.showMessageDialog(
                    this,
                    "Colaborador agregado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al agregar el colaborador.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void agregarProveedor() {
        try {
            String nombreEmpresa = JOptionPane.showInputDialog(this, "Nombre de la empresa proveedora:", "Nuevo Proveedor", JOptionPane.QUESTION_MESSAGE);
            if (nombreEmpresa == null) return;

            String rubro = JOptionPane.showInputDialog(this, "Rubro del proveedor:", "Nuevo Proveedor", JOptionPane.QUESTION_MESSAGE);
            if (rubro == null) return;

            String contactoPrincipal = JOptionPane.showInputDialog(this, "Nombre de la persona de contacto:", "Nuevo Proveedor", JOptionPane.QUESTION_MESSAGE);
            if (contactoPrincipal == null) return;

            String telefono = JOptionPane.showInputDialog(this, "Teléfono de contacto:", "Nuevo Proveedor", JOptionPane.QUESTION_MESSAGE);
            if (telefono == null) return;

            Proveedor proveedor = new Proveedor(
                    nombreEmpresa,
                    rubro,
                    contactoPrincipal,
                    telefono
            );

            gestor.getEntidades().add(proveedor);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor agregado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al agregar el proveedor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void agregarProducto() {
        try {
            String nombreProducto = JOptionPane.showInputDialog(this, "Nombre del producto:", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (nombreProducto == null) return;

            String tipoProducto = JOptionPane.showInputDialog(this, "Tipo de producto (Ej: Salmón Atlántico):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (tipoProducto == null) return;

            String comuna = JOptionPane.showInputDialog(this, "Comuna asociada al producto:", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (comuna == null) return;

            String cantidadStr = JOptionPane.showInputDialog(this, "Cantidad (número):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (cantidadStr == null) return;

            double cantidad = Double.parseDouble(cantidadStr);

            String nombreUnidadOrigen = JOptionPane.showInputDialog(this, "Unidad de origen (nombre centro/planta):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (nombreUnidadOrigen == null) return;

            UnidadOperativa unidadOrigen = new UnidadOperativa(nombreUnidadOrigen, comuna);

            Producto producto = new Producto(
                    nombreProducto,
                    comuna,
                    tipoProducto,
                    cantidad,
                    unidadOrigen
            );

            gestor.getEntidades().add(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto agregado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La cantidad debe ser un número válido.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al agregar el producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================
    //  RESUMENES EN GUI (TABLA)
    // ==========================

    /**
     * Muestra todas las entidades en una vista tipo tabla monoespaciada.
     */
    private void mostrarResumenGeneralGUI() {
        ArrayList<Registrable> entidades = gestor.getEntidades();
        if (entidades.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay entidades registradas.",
                    "Sin datos",
                    JOptionPane.INFORMATION_MESSAGE
            );
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
            } else if (r instanceof Proveedor proveedor) {
                identificador = proveedor.getNombreEmpresa();
                detalle = proveedor.getRubro();
            } else if (r instanceof Producto producto) {
                identificador = producto.getNombre();
                detalle = producto.getTipoProducto() + " (" + producto.getCantidad() + ")";
            } else if (r instanceof UnidadOperativa uo) {
                identificador = uo.getNombre();
                detalle = "Comuna: " + uo.getComuna();
            } else {
                identificador = r.getClass().getSimpleName();
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
     * Muestra solo las entidades de un tipo específico (Colaborador, Proveedor, Producto)
     * en una vista tipo tabla.
     */
    private void mostrarResumenFiltrado(Class<?> tipoFiltro, String titulo) {
        ArrayList<Registrable> entidades = gestor.getEntidades();

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
            } else if (r instanceof Proveedor proveedor) {
                identificador = proveedor.getNombreEmpresa();
                detalle = proveedor.getRubro();
            } else if (r instanceof Producto producto) {
                identificador = producto.getNombre();
                detalle = producto.getTipoProducto() + " (" + producto.getCantidad() + ")";
            } else if (r instanceof UnidadOperativa uo) {
                identificador = uo.getNombre();
                detalle = "Comuna: " + uo.getComuna();
            } else {
                identificador = r.getClass().getSimpleName();
                detalle = r.toString();
            }

            sb.append(String.format("%-15s | %-20s | %-30s%n",
                    tipo,
                    recortar(identificador, 20),
                    recortar(detalle, 30)
            ));
        }

        if (!hayDatos) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay entidades registradas del tipo: " + titulo,
                    "Sin datos",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        mostrarTextoEnVentana(sb.toString(), "Resumen de " + titulo);
    }

    private void mostrarTextoEnVentana(String texto, String titulo) {
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                titulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private String recortar(String texto, int largoMax) {
        if (texto == null) return "";
        return texto.length() <= largoMax ? texto : texto.substring(0, largoMax - 3) + "...";
    }
}
