/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion.modelo.pojo;

/**
 *
 * @author andre
 */
public class RespuestaLogin {
    private boolean error;
    private String contenido;
    private Medico medicoSesion;

    public RespuestaLogin() {
    }

    public RespuestaLogin(boolean error, String contenido, Medico medicoSesion) {
        this.error = error;
        this.contenido = contenido;
        this.medicoSesion = medicoSesion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
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
