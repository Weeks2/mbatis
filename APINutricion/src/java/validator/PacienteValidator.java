/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import modelo.pojo.Paciente;

/**
 *
 * @author andre
 */
public class PacienteValidator {

    public boolean isValid(Paciente paciente) {
        if(paciente.getNombre() == null) {
            return false;
        }
        return paciente.getEmail() != null;
    }
    
}
