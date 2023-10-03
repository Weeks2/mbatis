package ws;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("pacientes")
public class PacientesWS {
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("prueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String prueba(){
        return "Hola desde pacientes...";
    }
   
}
