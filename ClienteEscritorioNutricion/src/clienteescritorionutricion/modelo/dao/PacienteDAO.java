/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion.modelo.dao;

import clienteescritorionutricion.modelo.ConexionHTTP;
import clienteescritorionutricion.modelo.pojo.CodigoHTTP;
import clienteescritorionutricion.modelo.pojo.Mensaje;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class PacienteDAO {
    
    public static HashMap<String, Object> obtenerPacientesPorMedico(int idMedico){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String url = Constantes.URL_WS + "paciente/obtenerPorMedico/" + idMedico;
        CodigoHTTP  codigoRespuesta = ConexionHTTP.peticionGET(url);
        
        if(codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            respuesta.put("error", false);
            Type tipoListaPaciente = new TypeToken<List<Paciente>>(){}.getType();
            Gson gson = new Gson();
            List<Paciente> pacientes = gson.fromJson(codigoRespuesta.getContenido(), tipoListaPaciente);
            respuesta.put("pacientes", pacientes);
        }else{
            respuesta.put("error", true);
            respuesta.put("mensaje", "Hubo un error al obtener la información de los pacientes, " + "porfavor intentelo más tarde.");
        }
        return respuesta;
    }
    
 
    public static Mensaje registrarPaciente(Paciente paciente){
        Mensaje msj = new Mensaje();        
        CodigoHTTP respuestaWS = ConexionHTTP.postRequest(Constantes.URL_WS + "paciente/registrar", paciente);
        
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la petición para el registro del Paciente.");
        }
        return msj;
    }
}
