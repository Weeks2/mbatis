package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class PacienteDAO {
    public List<Paciente> obtenerPacientePorIdMedico(Integer idMedico){
        List<Paciente> paciente = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if(conexionDB != null){
            try{
                paciente = conexionDB.selectList("paciente.obtenerPorIdMedico", idMedico);
            }catch (Exception e){
            } finally{
                conexionDB.close();
            }
        }
        return paciente;
    }
    
    public Mensaje registrarPaciente(Paciente paciente){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("paciente.registrarPaciente", paciente);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("OK, " +numeroFilasAfectadas + ","+paciente.getNombre());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar la información del Paciente.");
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
    
    private HashMap<String, Object> toparam(Paciente paciente) {
    HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idPaciente", paciente.getIdPaciente());
        parametros.put("nombre", paciente.getNombre());
        parametros.put("apellidoPaterno", paciente.getApellidoPaterno());
        parametros.put("apellidoMaterno", paciente.getApellidoMaterno());
        parametros.put("fechaNacimiento", paciente.getFechaNacimiento());
        parametros.put("sexo", paciente.getSexo());
        parametros.put("peso", paciente.getPeso());
        parametros.put("estatura", paciente.getEstatura());
        parametros.put("tallaInicial", paciente.getTallaInicial());
        parametros.put("telefono", paciente.getTelefono());
        parametros.put("password", paciente.getPassword());
        parametros.put("fotografia", paciente.getFotografia());
        parametros.put("idDomicilio", paciente.getIdDomicilio());
        parametros.put("idMedico", paciente.getIdMedico());

       return parametros;
    }
    
    public Mensaje editarPaciente(Paciente paciente){
        
        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(paciente);
        SqlSession conn = MyBatisUtil.getSesion();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                Paciente pacienteFound = conn.selectOne("paciente.obtenerPacientePorId", paciente.getIdPaciente());
                if (pacienteFound != null) {
                    int afected = conn.update("paciente.editarPaciente", parametros);
                    conn.commit();
                    if (afected > 0) {
                        response.setMensaje("Paciente actualizado con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar la información del Paciente.");
                    }
                }
            } catch (Exception e) {
                response.setMensaje("Error: " + e.getMessage());
            } finally {
                conn.close();
            }
        } else {
            response.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return response;
    }
    
    public Mensaje eliminarPaciente(Integer idPaciente) {

        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSesion();

        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.delete("paciente.eliminarPaciente", (idPaciente));
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información del Paciente eliminada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo eliminar la información del Paciente.");
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
}
