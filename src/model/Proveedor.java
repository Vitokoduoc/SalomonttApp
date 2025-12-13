package model;

/**
 * Representa un proveedor de la empresa SalmonApp.
 *
 * <p>
 * Un proveedor puede ser una empresa externa que suministra insumos,
 * servicios o materiales para la operación. Se registran datos básicos
 * de identificación y contacto.
 * </p>
 */
public class Proveedor implements Registrable {

    private String nombreEmpresa;
    private String rubro;
    private String contactoPrincipal;
    private String telefonoContacto;

    /**
     * Construye un proveedor con sus datos básicos.
     *
     * @param nombreEmpresa     nombre de la empresa proveedora
     * @param rubro             rubro o giro del proveedor
     * @param contactoPrincipal nombre de la persona de contacto
     * @param telefonoContacto  teléfono del contacto principal
     */
    public Proveedor(String nombreEmpresa,
                     String rubro,
                     String contactoPrincipal,
                     String telefonoContacto) {
        this.nombreEmpresa = nombreEmpresa;
        this.rubro = rubro;
        this.contactoPrincipal = contactoPrincipal;
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getRubro() {
        return rubro;
    }

    public String getContactoPrincipal() {
        return contactoPrincipal;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    @Override
    public void mostrarResumen() {
        System.out.println(
                "Proveedor: " + nombreEmpresa +
                        " | Rubro: " + rubro +
                        " | Contacto: " + contactoPrincipal +
                        " | Tel: " + telefonoContacto
        );
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombreEmpresa='" + nombreEmpresa + '\'' +
                ", rubro='" + rubro + '\'' +
                ", contactoPrincipal='" + contactoPrincipal + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }
}
