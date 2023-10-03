package modelo.pojo;

public class Medico {

    private Integer idMedico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMterno;
    private String fechaNacimineto;
    private String sexo;
    private String numeroDePersonal;
    private String numeroCedulaProfecional;
    private String password;
    private Integer idRol;
    private Integer idDomicilio;
    private byte[] fotografia;

    public Medico() {
    }

    public Medico(Integer idMedico, String nombre, String apellidoPaterno, String apellidoMterno, String fechaNacimineto, String sexo, String numeroDePersonal, String numeroCedulaProfecional, String password, Integer idRol, Integer idDomicilio, byte[] fotografia) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMterno = apellidoMterno;
        this.fechaNacimineto = fechaNacimineto;
        this.sexo = sexo;
        this.numeroDePersonal = numeroDePersonal;
        this.numeroCedulaProfecional = numeroCedulaProfecional;
        this.password = password;
        this.idRol = idRol;
        this.idDomicilio = idDomicilio;
        this.fotografia = fotografia;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMterno() {
        return apellidoMterno;
    }

    public void setApellidoMterno(String apellidoMterno) {
        this.apellidoMterno = apellidoMterno;
    }

    public String getFechaNacimineto() {
        return fechaNacimineto;
    }

    public void setFechaNacimineto(String fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        this.numeroDePersonal = numeroDePersonal;
    }

    public String getNumeroCedulaProfecional() {
        return numeroCedulaProfecional;
    }

    public void setNumeroCedulaProfecional(String numeroCedulaProfecional) {
        this.numeroCedulaProfecional = numeroCedulaProfecional;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }
}
