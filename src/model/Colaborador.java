package model;

/**
 * Representa a un colaborador interno de la empresa.
 */
public class Colaborador extends Persona implements Registrable {

    private UnidadOperativa unidadOperativa;
    private String area;
    private String cargo;

    /**
     * Constructor completo de Colaborador.
     *
     * @param nombre nombre del colaborador
     * @param rut rut del colaborador
     * @param email correo electrónico
     * @param direccion domicilio del colaborador
     * @param unidadOperativa unidad operativa donde trabaja
     * @param area área de desempeño
     * @param cargo cargo laboral
     */
    public Colaborador(String nombre,
                       String rut,
                       String email,
                       String direccion,
                       UnidadOperativa unidadOperativa,
                       String area,
                       String cargo) {

        super(nombre, rut, email, direccion);
        this.unidadOperativa = unidadOperativa;
        this.area = area;
        this.cargo = cargo;
    }

    public UnidadOperativa getUnidadOperativa() {
        return unidadOperativa;
    }

    public String getArea() {
        return area;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println(
                "Colaborador: " + nombre +
                        " | Cargo: " + cargo +
                        " | Área: " + area +
                        " | Unidad: " + unidadOperativa.getNombre() +
                        " | Dirección: " + direccion
        );
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", email='" + email + '\'' +
                ", dirección='" + direccion + '\'' +
                ", unidadOperativa=" + unidadOperativa.getNombre() +
                ", area='" + area + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
