package model.persona;

/**
 * Representa una persona dentro del sistema SalmonttApp.
 * Clase abstracta que encapsula atributos comunes a todas las personas.
 *
 * Autor: Víctor Valenzuela
 */
public abstract class Persona {

    protected String nombre;
    protected Rut rut;
    protected String email;
    protected Direccion direccion;

    /**
     * Constructor base de Persona.
     *
     * @param nombre nombre completo
     * @param rut RUT de la persona
     * @param email correo electrónico
     * @param direccion dirección de la persona
     */
    public Persona(String nombre, Rut rut, String email, Direccion direccion) {
        setNombre(nombre);
        setRut(rut);
        setEmail(email);
        setDireccion(direccion);
    }

    /**
     * Constructor vacío para escenarios de carga o herencia.
     */
    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public Rut getRut() {
        return rut;
    }

    protected void setRut(Rut rut) {
        if (rut == null) {
            throw new IllegalArgumentException("El RUT no puede ser null.");
        }
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        this.email = email.trim();
    }

    public Direccion getDireccion() {
        return direccion;
    }

    protected void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser null.");
        }
        this.direccion = direccion;
    }
}
