package model;

/**
 * Clase que representa un Tour, registrando información básica como
 * el nombre de la ruta, el responsable de la actividad y la fecha programada.
 *
 * Autor:Víctor Valenzuela
 */
public class Tour {

    /**
     * Atributo que almacena el nombre de la ruta del tour.
     */
    private String nomRuta;
    private String responsable;
    private String fecha;

    /**
     * Constructor vacío, pensado para escenarios donde los datos
     * se asignarán posteriormente mediante los métodos setter.
     */
    public Tour() {
    }

    /**
     * Constructor principal del tour. Recibe los datos esenciales y aplica
     * las validaciones mínimas necesarias para asegurar que el objeto
     * se inicialice con información válida.
     *
     * @param nomRuta      nombre de la ruta; no puede ser nulo ni vacío
     * @param responsable  responsable de la actividad; no puede estar vacío
     * @param fecha        fecha programada; no debe ser nula
     * @throws IllegalArgumentException cuando alguno de los campos es inválido
     */
    public Tour(String nomRuta, String responsable, String fecha) {
        setNomRuta(nomRuta);
        setResponsable(responsable);
        setFecha(fecha);
    }

    /**
     * Getter y setter de los atributos del tour.
     */
    public String getNomRuta() {
        return nomRuta;
    }

    public void setNomRuta(String nomRuta) {
        if (nomRuta == null || nomRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ruta no puede estar vacío.");
        }
        this.nomRuta = nomRuta.trim();
    }
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        if (responsable == null || responsable.trim().isEmpty()) {
            throw new IllegalArgumentException("El responsable no puede estar vacío.");
        }
        this.responsable = responsable.trim();
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.fecha = fecha.trim();
    }

    /**
     * Devuelve una representación en texto del tour, mostrando
     * la información más relevante de la ruta.
     *
     * @return texto con los detalles del tour
     */
    @Override
    public String toString() {
        return "Tour [nomRuta=" + nomRuta +
                ", responsable=" + responsable +
                ", fecha=" + fecha + "]";
    }
}
