package modelo.pojo;

import modelo.pojo.Medico;

/**
 *
 * @author andre
 */
public class RespuestaLoginEscritorio {
    private Boolean error;
    private String contenido;
    private Medico medicoSesion;

    public RespuestaLoginEscritorio() {
    }

    public RespuestaLoginEscritorio(Boolean error, String contenido, Medico medicoSesion) {
        this.error = error;
        this.contenido = contenido;
        this.medicoSesion = medicoSesion;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Medico getMedicoSesion() {
        return medicoSesion;
    }

    public void setMedicoSesion(Medico medicoSesion) {
        this.medicoSesion = medicoSesion;
    }
}
