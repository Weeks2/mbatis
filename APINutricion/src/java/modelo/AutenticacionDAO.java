package modelo;

import java.util.HashMap;
import modelo.pojo.Medico;
import modelo.pojo.RespuestaLoginEscritorio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

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
            e.printStackTrace();
        }
        return respuesta;
    }
}
