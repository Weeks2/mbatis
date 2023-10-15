package validator;

import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;

public class PacienteValidator {
    
    public static Mensaje isValid(Paciente paciente) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");

        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            response.setMensaje("Nombre");
        }
        
        if (paciente.getApellidoPaterno() == null || paciente.getApellidoPaterno().isEmpty()) {
            response.setMensaje("Apellido Paterno");
        }
        
        if (paciente.getApellidoMaterno() == null || paciente.getApellidoMaterno().isEmpty()) {
            response.setMensaje("Apellido Materno");
        }

        if(!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() +  " no puede ser vacio");
        }

        return response;
    }
    
}
