/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.mysql.cj.protocol.Message;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;

/**
 *
 * @author andre
 */
public class PacienteValidator {

    public static Mensaje isValid(Paciente paciente) {
        Mensaje msj = new Mensaje();
        msj.setError(false);
        msj.setMensaje("OK, ");

        if(paciente.getNombre() == null) {
            msj.setError(true);
           msj.setMensaje("Nombre debe tener valor");
        }

        return msj;
    }
    
}
