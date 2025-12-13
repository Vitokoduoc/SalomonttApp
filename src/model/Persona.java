package model;

/**
 * Representa una persona dentro del sistema SalmonttApp.
 */
public abstract class Persona {

    protected String nombre;
    protected String rut;
    protected String email;
    protected String direccion;

    /**
     * Constructor base de Persona.
     *
     * @param nombre nombre completo
     * @param rut RUT del colaborador
     * @param email correo electrónico
     * @param direccion dirección del domicilio
     */
    public Persona(String nombre, String rut, String email, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }
}
