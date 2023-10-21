/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion;

import clienteescritorionutricion.modelo.dao.PacienteDAO;
import clienteescritorionutricion.modelo.pojo.Mensaje;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Utilidades;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLEditarPacienteController implements Initializable {
    
    private Paciente infoPaciente;
    
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarInformacionPaciente(Paciente infoPaciente){
        this.infoPaciente = infoPaciente;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimientoPaciente = LocalDate.parse(infoPaciente.getFechaNacimiento(), formatter);
        tfNombre.setText(infoPaciente.getNombre());
        tfApellidoPaterno.setText(infoPaciente.getApellidoPaterno());
        tfApellidoMaterno.setText(infoPaciente.getApellidoMaterno());
        dpFechaNacimiento.setValue(fechaNacimientoPaciente);
        tfEmail.setDisable(true);
        tfEmail.setText(infoPaciente.getEmail());
        tfTelefono.setText(infoPaciente.getTelefono());
    }
    
    private void btnRegistrarPaciente(ActionEvent event) {
        Paciente pacienteNuevo = new Paciente();
        pacienteNuevo.setNombre(tfNombre.getText());
        pacienteNuevo.setApellidoPaterno(tfApellidoPaterno.getText());
        pacienteNuevo.setApellidoMaterno(tfApellidoMaterno.getText());
        pacienteNuevo.setFechaNacimiento(dpFechaNacimiento.getValue().toString());
        pacienteNuevo.setSexo("");
        pacienteNuevo.setPeso(1.5F);
        pacienteNuevo.setEstatura(0.1F);
        pacienteNuevo.setTallaInicial(50);
        pacienteNuevo.setEmail(tfEmail.getText());
        pacienteNuevo.setTelefono(tfTelefono.getText());
        pacienteNuevo.setPassword("");
        pacienteNuevo.setIdMedico(1);
        
        registrarPaciente(pacienteNuevo);
        
    }
    
    private void verificarPaciente (){
        
    }
    
    private void registrarPaciente (Paciente pacienteNuevo){
        Mensaje msj = PacienteDAO.registrarPaciente(pacienteNuevo);
        
        if (!msj.isError()) {
            Utilidades.mostrarAlertaSimple("Paciente registrado", msj.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
}
