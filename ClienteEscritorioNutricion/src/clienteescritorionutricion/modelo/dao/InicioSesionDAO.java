/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion.modelo.dao;

import clienteescritorionutricion.modelo.ConexionHTTP;
import clienteescritorionutricion.modelo.pojo.CodigoHTTP;
import clienteescritorionutricion.modelo.pojo.RespuestaLogin;
import clienteescritorionutricion.utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

/**
 *
 * @author andre
 */
public class InicioSesionDAO {
    
    public static RespuestaLogin validarSesion(String numeroDePersonal, String password){
        RespuestaLogin respuestaWS = new RespuestaLogin();
        String url = Constantes.URL_WS + "autenticacion/loginEscritorio";
        String parametros = String.format("numeroDePersonal=%s&password=%s", numeroDePersonal, password);
        CodigoHTTP respuestaConexion = ConexionHTTP.peticionPOST(url, parametros);
        
        if(respuestaConexion.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            //Utilizamos GSON
            Gson gson = new Gson();
            respuestaWS = gson.fromJson(respuestaConexion.getContenido(), RespuestaLogin.class);
        }else{
            respuestaWS.setError(true);
            respuestaWS.setContenido("Hubo un error al realizar la petición, porfaver intentelo más tarde.");
        }
        return respuestaWS;
    }
}
