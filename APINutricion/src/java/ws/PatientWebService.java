package ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.pojo.AutenticacionDAO;
import modelo.pojo.PatientResponse;
import javax.activation.DataSource;

@Path("patient")
public class PatientWebService {
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public PatientResponse fetchAll(){
        // return AutenticacionDAO.fetchPatients();
        return  AutenticacionDAO.fetchData();
    }
}
