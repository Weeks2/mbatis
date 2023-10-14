package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import modelo.pojo.Paciente;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * http://localhost:8084/APINutricion/api/paciente/obtenerPorMedico/2
 * 
 * GET /obtenerPorMedico/Id
 * POST /agregarPaciente
 * PUT /editarPaciente, 
 * DELETE /eliminarPacientePorId
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
               e.printStackTrace();
            } finally{
                conexionDB.close();
            }
        }
        return paciente;
    }
}
