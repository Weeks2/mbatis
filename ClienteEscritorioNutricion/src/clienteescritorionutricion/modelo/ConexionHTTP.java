/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion.modelo;

import clienteescritorionutricion.modelo.pojo.CodigoHTTP;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL; 
/**
 *
 * @author andre
 */
public class ConexionHTTP {

       /**
        * 
        * @param paciente
        * @return 
        * private static String toJson(Paciente paciente) {
          ObjectMapper objectMapper = new ObjectMapper();
          return objectMapper.writeValueAsString(paciente);
       }
    
        */
    
       
       public static CodigoHTTP request(String method,String url ) {

        CodigoHTTP respuesta = new CodigoHTTP();
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod(method);
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }
    public static CodigoHTTP peticionGET(String url) {

        CodigoHTTP respuesta = new CodigoHTTP();

        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("GET");
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }

    public static CodigoHTTP peticionPOST(String url, String parametros) {

        CodigoHTTP respuesta = new CodigoHTTP();
        
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la peticion
            OutputStream os = conexionHttp.getOutputStream(); //accesso para enviar datos
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            //Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }
    
    public static CodigoHTTP peticionPUT(String url, String parametros){
        CodigoHTTP respuesta = new CodigoHTTP();
        
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la peticion
            OutputStream os = conexionHttp.getOutputStream(); //accesso para enviar datos
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            //Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }
    
    public static CodigoHTTP peticionDELETE(String url, String parametros){
        CodigoHTTP respuesta = new CodigoHTTP();
        
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlServicio.openConnection();
            conexionHttp.setRequestMethod("DELETE");
            conexionHttp.setRequestProperty("Content-Type", "application/json");
            conexionHttp.setDoOutput(true);
            //Escribir datos en el cuerpo de la peticion
            OutputStream os = conexionHttp.getOutputStream(); //accesso para enviar datos
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            //Termina la escritura
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(convertirContenido(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("CODE ERROR: " + codigoRespuesta);
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error: " + ex.getMessage());
        } catch (IOException iox) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: " + iox.getMessage());
        }
        return respuesta;
    }

    private static String convertirContenido(InputStream contenido) throws IOException {
        InputStreamReader inputLectura = new InputStreamReader(contenido);
        BufferedReader buffer = new BufferedReader(inputLectura);
        String cadenaEntrada;
        StringBuffer cadenaBuffer = new StringBuffer();
        while ((cadenaEntrada = buffer.readLine()) != null) {
            cadenaBuffer.append(cadenaEntrada);
        }
        buffer.close();
        return cadenaBuffer.toString();
    }
}
