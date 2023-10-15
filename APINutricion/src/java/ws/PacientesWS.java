package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * http://localhost:8084/APINutricion/api/paciente/obtenerPorMedico/2
 * 
 * GET /obtenerPorMedico/Id
 * POST /agregarPaciente: y verificar que el correo no se repita
 * PUT /editarPaciente, no se podra editar el correo
 * DELETE /eliminarPacientePorId
 * 
 * {
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
    "password": "berenice23"
}
 */
@Path("paciente")
public class PacientesWS {
    
    @Context
    private UriInfo context;
    
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
        List<Paciente> paciente = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                paciente = conexionDB.selectList("paciente.obtenerPorIdMedico", idMedico);
            }catch (Exception e){
            } finally{
                conexionDB.close();
            }
        }
        return paciente;
    }
    
    @POST
    @Path("agregarPaciente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarPaciente(Paciente paciente){
        PacienteDAO dao = new PacienteDAO();
        return dao.registrarPaciente(paciente);
    }
}
