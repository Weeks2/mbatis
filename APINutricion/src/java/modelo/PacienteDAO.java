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
    
    public Mensaje registrarPaciente(String nombre, String apellidoPaterno, String apellidoMaterno,
                                      String fechaNacimiento, String sexo, float peso, float estatura,
                                      int tallaInicial, String email, String telefono, String password,
                                      byte[] fotografia, Integer idDomicilio, Integer idMedico){
        
        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellidoPaterno(apellidoPaterno);
        paciente.setApellidoMaterno(apellidoMaterno);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setSexo(sexo);
        paciente.setPeso(peso);
        paciente.setEstatura(estatura);
        paciente.setTallaInicial(tallaInicial);
        paciente.setEmail(email);
        paciente.setTelefono(telefono);
        paciente.setPassword(password);
        paciente.setFotografia(fotografia);
        paciente.setIdDomicilio(idDomicilio);
        paciente.setIdMedico(idMedico);
        
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("paciente.registrarPaciente", paciente);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Información del Paciente registrado con éxito");
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
    
    public Mensaje editarPaciente(Integer idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno,
                                  String fechaNacimiento, String sexo, float peso, float estatura, int tallaInicial,
                                  String telefono, String password, byte[] fotografia, Integer idDomicilio,
                                  Integer idMedico){
        
        Mensaje msj = new Mensaje();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idPaciente", idPaciente);
        parametros.put("nombre", nombre);
        parametros.put("apellidoPaterno", apellidoPaterno);
        parametros.put("apellidoMaterno", apellidoMaterno);
        parametros.put("fechaNacimiento", fechaNacimiento);
        parametros.put("sexo", sexo);
        parametros.put("peso", peso);
        parametros.put("estatura", estatura);
        parametros.put("tallaInicial", tallaInicial);
        parametros.put("telefono", telefono);
        parametros.put("password", password);
        parametros.put("fotografia", fotografia);
        parametros.put("idDomicilio", idDomicilio);
        parametros.put("idMedico", idMedico);

        SqlSession conexionDB = MyBatisUtil.getSesion();

        if (conexionDB != null) {
            try {
                Paciente usuaerioExistente = conexionDB.selectOne("paciente.obtenerPacientePorId", idPaciente);
                if (usuaerioExistente != null) {
                    int numeroFilasAfectadas = conexionDB.update("paciente.editarPaciente", parametros);
                    conexionDB.commit();
                    if (numeroFilasAfectadas > 0) {
                        msj.setError(false);
                        msj.setMensaje("Paciente actualizado con éxito.");
                    } else {
                        msj.setError(true);
                        msj.setMensaje("Lo sentimos, no se pudo actualizar la información del Paciente.");
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
    
    public Mensaje eliminarUsuario(Integer idPaciente) {

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
