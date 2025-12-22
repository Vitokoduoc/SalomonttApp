package model.persona;

import model.interfaces.Registrable;

/**
 * Representa un proveedor de la empresa SalmonApp.
 *
 * Autor: Víctor Valenzuela
 */
public class Proveedor implements Registrable {

    private String nombreEmpresa;
    private String rubro;
    private String contactoPrincipal;
    private String telefonoContacto;

    public Proveedor(String nombreEmpresa,
                     String rubro,
                     String contactoPrincipal,
                     String telefonoContacto) {
        setNombreEmpresa(nombreEmpresa);
        setRubro(rubro);
        setContactoPrincipal(contactoPrincipal);
        setTelefonoContacto(telefonoContacto);
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        if (nombreEmpresa == null || nombreEmpresa.isBlank()) {
            throw new IllegalArgumentException("El nombre de la empresa no puede estar vacío.");
        }
        this.nombreEmpresa = nombreEmpresa.trim();
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        if (rubro == null || rubro.isBlank()) {
            throw new IllegalArgumentException("El rubro no puede estar vacío.");
        }
        this.rubro = rubro.trim();
    }

    public String getContactoPrincipal() {
        return contactoPrincipal;
    }

    public void setContactoPrincipal(String contactoPrincipal) {
        if (contactoPrincipal == null || contactoPrincipal.isBlank()) {
            throw new IllegalArgumentException("El contacto principal no puede estar vacío.");
        }
        this.contactoPrincipal = contactoPrincipal.trim();
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        if (telefonoContacto == null || telefonoContacto.isBlank()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.telefonoContacto = telefonoContacto.trim();
    }

    /**
     * Implementación del método genérico de la interfaz Registrable.
     */
    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    /**
     * Registro simbólico del proveedor.
     */
    @Override
    public void registrar() {
        System.out.println("Proveedor registrado: " + nombreEmpresa);
    }

    /**
     * Representación tabular del proveedor para salida en consola.
     */
    @Override
    public String toString() {
        return String.format(
                "%-22s | %-15s | %-18s | %-14s |",
                nombreEmpresa,
                rubro,
                contactoPrincipal,
                telefonoContacto
        );
    }
}
