package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.pojo.AutenticacionDAO;
import modelo.pojo.RespuestaLoginEscritorio;
//import modelo.AutenticacionDAO;
//import modelo.pojo.RespuestaLoginEscritorio;
//import static org.glassfish.jersey.server.model.Parameter.Source.PATH;

@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    public AutenticacionWS() {
    }
    
   
    @POST
    @Path("loginEscritorio")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio iniciarSesionEscritorio(@FormParam("numeroDePersonal") String numeroDePersonal,
                                                            @FormParam("password") String password) {
        try {
             RespuestaLoginEscritorio respuesta = null;
        if (numeroDePersonal.isEmpty() && password.isEmpty()) {
            respuesta = AutenticacionDAO.verificarSesionEscritorio(numeroDePersonal, password);
        } 
        return respuesta;
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
             throw new WebApplicationException(e.getCause()+":" + e.getMessage(),Response.Status.BAD_REQUEST);
        }
    }
}