/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Usuario;

/**
 * REST Web Service
 *
 * @author andre
 */
@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }
    @GET
    @Path("listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> obatenerUsuarios(){
        List<Usuario> usuarios = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                usuarios = conexionDB.selectList("usuarios.obtenerUsuarios");
            }catch (Exception e){
               e.printStackTrace();
            } finally{
                conexionDB.close();
            }
        }
        
        return usuarios;
    }
    
    @GET
    @Path("usuarioId/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario obtenerUsuarioPorId(@PathParam("idUsuario") Integer idUsuario){
        Usuario usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                usuario = conexionDB.selectOne("usuarios.obtenerPorId", idUsuario);
            }catch (Exception e){
               e.printStackTrace();
            } finally{
                conexionDB.close();
            }
        }
        return usuario;
    }
    
    @GET
    @Path("usuarioNombre/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario obtenerUsuarioPorNombre(@PathParam("nombreUsuario") String nombreUsuario){
        Usuario usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                usuario = conexionDB.selectOne("usuarios.obtenerPorNombre", nombreUsuario);
            }catch (Exception e){
               e.printStackTrace();
            } finally{
                conexionDB.close();
            }
        }
        return usuario;
    }
    
    @GET
    @Path("usuarioCorreo/{correoUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario obtenerUsuarioPorCorreo(@PathParam("correoUsuario") String correoUsuario){
        Usuario usuario = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                usuario = conexionDB.selectOne("usuarios.obtenerPorCorreo", correoUsuario);
            }catch (Exception e){
               e.printStackTrace();
            } finally{
                conexionDB.close();
            }
        }
        return usuario;
    }
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUsuario(@FormParam("nombre") String nombre,
                                    @FormParam("apellidoPaterno") String apellidoPaterno,
                                    @FormParam("apellidoMaterno") String apellidoMaterno,
                                    @FormParam("correo") String correo,
                                    @FormParam("password") String password){
                                    
        
    Usuario usuario = new Usuario();
    usuario.setNombre(nombre);
    usuario.setApellidoPaterno(apellidoPaterno);
    usuario.setApellidoMaterno(apellidoMaterno);
    usuario.setCorreo(correo);
    usuario.setPassword(password);
    
    Mensaje msj = new Mensaje();
    SqlSession conexionDB = MyBatisUtil.getSesion();
    
    if(conexionDB != null){
        try{
            int numeroFilasAfectadas = conexionDB.insert("usuarios.registrar", usuario);
            conexionDB.commit();
            if(numeroFilasAfectadas > 0){
                msj.setError(false);
                msj.setMensaje("Información del Usuario registrada con éxito");
            }else{
                msj.setError(true);
                msj.setMensaje("Lo sentimos, no se pudo registrar la información del Usuario.");
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error: " + e.getMessage());
        } finally{
            conexionDB.close();
        }
    } else{
        msj.setError(true);
        msj.setMensaje("Por el momento no hay conexión con la base de datos.");
    }
    
    return msj;
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUsuario(@FormParam("nombre") String nombre,
                                 @FormParam("idUsuario") Integer idUsuario,
                                 @FormParam("apellidoPaterno") String apellidoPaterno,
                                 @FormParam("apellidoMaterno") String apellidoMaterno,
                                 @FormParam("correo") String correo,
                                 @FormParam("password") String password){
        
        Mensaje msj = new Mensaje();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idUsuario", idUsuario);
        parametros.put("nombre", nombre);
        parametros.put("apellidoPaterno", apellidoPaterno);
        parametros.put("apellidoMaterno", apellidoMaterno);
        parametros.put("password", password);
        
        SqlSession conexionDB = MyBatisUtil.getSesion();
    
        if (conexionDB != null) {
            try {
                Usuario usuaerioExistente = conexionDB.selectOne("usuarios.obtenerPorId", idUsuario);
                if (usuaerioExistente != null) {
                    int numeroFilasAfectadas = conexionDB.update("usuarios.editar", parametros);
                    conexionDB.commit();
                    if (numeroFilasAfectadas > 0) {
                        msj.setError(false);
                        msj.setMensaje("Usuario actualizado con éxito.");
                    } else {
                        msj.setError(true);
                        msj.setMensaje("Lo sentimos, no se pudo actualizar la información del Usuario.");
                    }
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return msj;
    }
    
    @DELETE
    @Path("eliminar/{idUsuario}")
    @Produces(MediaType.TEXT_PLAIN)
    public Mensaje eliminarUsuario(@FormParam("idUsuario") Integer idUsuario){
    
    Mensaje msj = new Mensaje();
    SqlSession conexionDB = MyBatisUtil.getSesion();
    
    if(conexionDB != null){
        try{
            int numeroFilasAfectadas = conexionDB.delete("usuarios.eliminarUsuarioPorId", (idUsuario));
            conexionDB.commit();
            if(numeroFilasAfectadas > 0){
                msj.setError(false);
                msj.setMensaje("Información del Usuario eliminada con éxito");
            }else{
                msj.setError(true);
                msj.setMensaje("Lo sentimos, no se pudo eliminar la información del Usuario.");
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error: " + e.getMessage());
        } finally{
            conexionDB.close();
        }
    } else{
        msj.setError(true);
        msj.setMensaje("Por el momento no hay conexión con la base de datos.");
    }
    
    return msj;
    }
}
