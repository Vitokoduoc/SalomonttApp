package model.persona;

/**
 * Representa la dirección física de una entidad de SalmonttCorp.
 *
 * Autor: Víctor Valenzuela
 */
public class Direccion {

    private String calle;
    private String numero;
    private String comuna;
    private String region;

    public Direccion() {
    }

    public Direccion(String calle, String numero, String comuna, String region) {
        setCalle(calle);
        setNumero(numero);
        setComuna(comuna);
        setRegion(region);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = (calle == null) ? "" : calle.trim();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = (numero == null) ? "" : numero.trim();
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        if (comuna == null || comuna.isBlank()) {
            throw new IllegalArgumentException("La comuna no puede estar vacía.");
        }
        this.comuna = comuna.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = (region == null) ? "" : region.trim();
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s",
                calle, numero, comuna, region);
    }
}
