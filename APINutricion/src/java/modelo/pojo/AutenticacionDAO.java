/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

import java.util.HashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andre
 */
public class AutenticacionDAO {

    public static RespuestaLoginEscritorio verificarSesionEscritorio(String numeroDePersonal, String password) {
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(true);

        try (SqlSession conexionDB = MyBatisUtil.getSesion()) {

            if (conexionDB != null) {
                try {
                    HashMap<String, String> parametros = new HashMap<>();
                    parametros.put("numeroDePersonal", numeroDePersonal);
                    parametros.put("password", password);
                    Medico medico = conexionDB.selectOne("autenticacion.loginEscritorio", parametros);
                    
                    if (medico != null) {
                        respuesta.setError(false);
                        respuesta.setContenido("Bienvenido(a) " + medico.getNombre() + " al sistema de control de medicos.");
                        respuesta.setMedicoSesion(medico);
                    } else {
                        respuesta.setContenido("Número de personal y/o contraseña incorrectos, porfavor de verificar los datos.");
                    }
                } catch (Exception e) {
                    respuesta.setContenido("Error: " + e.getMessage());
                } finally {
                    conexionDB.close();
                }
            } else {
                respuesta.setContenido("Error: Por el momento no hay conexion a la base de datos");
            }
        } catch (Exception e) {
       }
        return respuesta;
    }
    
  public static PatientResponse fetchPatients() {
        PatientResponse patientResponse = new PatientResponse(null);
        
        try (SqlSession connection = MyBatisUtil.getSesion()) {
            if (connection != null) {
                try {
                    Patient patient = connection.selectOne("autenticacion.patientResponse");
                    patientResponse = new PatientResponse(patient);
                } 
                catch (Exception e) {} 
                finally {connection.close();}
            }  
            patientResponse.setMessageError("OK");
            return patientResponse;
        } catch (Exception e) {
           patientResponse.setMessageError(e.getMessage());
            return patientResponse;
        }
    }
  
  public static PatientResponse fetchData(){
   Patient patient = new Patient();
   patient.setNombre("Patient 1");
        PatientResponse response = new PatientResponse(patient);
        return response;
  }
}
