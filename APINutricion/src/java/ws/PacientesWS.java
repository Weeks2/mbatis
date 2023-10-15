package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import modelo.PacienteDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;
import validator.PacienteValidator;

/**
 *
 * http://localhost:8084/APINutricion/api/paciente/obtenerPorMedico/2
 * 
 * GET /obtenerPorMedico/Id
 * POST /agregarPaciente: y verificar que el correo no se repita
 * PUT /editarPaciente, no se podra editar el correo
 * DELETE /eliminarPacientePorId
 * 
{
    "idPaciente": 4,
    "nombre": "Berenice",
    "apellidoPaterno": "Carreon",
    "apellidoMaterno": "Reyes",
    "fechaNacimiento": "2002-09-30",
    "sexo": "F",
    "peso": 60.0,
    "estatura": 1.5,
    "tallaInicial": 50,
    "email": "berenice23@gmail.com",
    "telefono": "2245789545",
    "password": "berenice23",
    "idMedico": 2
}
 */
@Path("paciente")
public class PacientesWS {
    
    @Context
    private UriInfo context;

    //@Inject
    //private PacienteDAO pacienteDAO;
    
    @GET
    @Path("prueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String prueba(){
        return "Hola desde pacientes...";
    }
    
    @GET
    @Path("obtenerPorMedico/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> obtenerPacientePorIdMedico(@PathParam("idMedico") Integer idMedico){
      PacienteDAO dao = new PacienteDAO();
      return dao.obtenerPacientePorIdMedico(idMedico);
    }
    
    @POST
    @Path("agregarPaciente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarPaciente(Paciente paciente){
        Mensaje mensaje = PacienteValidator.isValid(paciente);
        if(mensaje.isError()) {
            return mensaje;
        }
        PacienteDAO dao = new PacienteDAO();
        return dao.registrarPaciente(paciente);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPaciente(Paciente paciente){
        Mensaje mensaje = PacienteValidator.isValid(paciente);
        if(mensaje.isError()) {
            return mensaje;
        }
        PacienteDAO dao = new PacienteDAO();
        return dao.editarPaciente(paciente);
    }
    
    @DELETE
    @Path("eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaciente(Paciente paciente){
        Mensaje mensaje = PacienteValidator.isValid(paciente);
        if(mensaje.isError()) {
            return mensaje;
        }
        PacienteDAO dao = new PacienteDAO();
        return dao.eliminarPaciente(paciente.getIdPaciente());
    }
}
