package controller;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dao.AutenticacionDAO;
import model.RespuestaLoginEscritorio;

/**
 * REST Web Service
 * http://localhost:8084/APINutricion/api/autenticacion/loginEscritorio
 * body = x-www-form-urlencoded
 * numeroDePersonal:231549
   password:elizabeth23
 * @author andre
 */
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
        RespuestaLoginEscritorio respuesta = null;
        if (!numeroDePersonal.isEmpty() && !password.isEmpty()) {
            respuesta = AutenticacionDAO.verificarSesionEscritorio(numeroDePersonal, password);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }
}