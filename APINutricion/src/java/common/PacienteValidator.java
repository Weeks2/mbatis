package common;

import model.Mensaje;
import model.Paciente;

public class PacienteValidator {

    private static boolean isNullOrEmpty (String val) {
       return val == null || val.isEmpty();
    }
    public static Mensaje isValid(Paciente paciente) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");

        if (isNullOrEmpty(paciente.getNombre())) {
            response.setMensaje("Nombre");
        }
        
        if (isNullOrEmpty(paciente.getApellidoPaterno())) {
            response.setMensaje("Apellido Paterno");
        }
        
        if (isNullOrEmpty(paciente.getApellidoMaterno())) {
            response.setMensaje("Apellido Materno");
        }
        
        if (isNullOrEmpty(paciente.getFechaNacimiento())){
            response.setMensaje("Fecha de Nacimiento");
        }

        if(!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() +  " no puede ser vacio");
        }

        return response;
    }
    
}
