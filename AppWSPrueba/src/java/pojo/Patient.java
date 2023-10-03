
package pojo;

/**
 *
 * @author andre
 */
public class Patient {

    private Integer idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String password;

    public Patient() {
    }

    public Patient(Integer idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.password = password;
    }
}
