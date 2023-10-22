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
import javafx.scene.control.Label;
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
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorApellidoPaterno;
    @FXML
    private Label lbErrorApellidoMaterno;
    @FXML
    private Label lbErrorFechaNacimiento;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarInformacionPaciente(Paciente infoPaciente){
        this.infoPaciente = infoPaciente;
        System.out.println(infoPaciente.getIdPaciente());
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
    
    @FXML
    private void btnUpdate(ActionEvent event) {
        
        infoPaciente.setNombre(tfNombre.getText());
        infoPaciente.setApellidoPaterno(tfApellidoPaterno.getText());
        infoPaciente.setApellidoMaterno(tfApellidoMaterno.getText());
        infoPaciente.setFechaNacimiento(dpFechaNacimiento.getValue().toString());
        infoPaciente.setEmail(tfEmail.getText());
        infoPaciente.setTelefono(tfTelefono.getText());        
        editarPaciente(infoPaciente);
        
    }
    
    private void verificarPaciente (){
        
    }
    
    private void editarPaciente (Paciente pacienteNuevo){
        Mensaje msj = PacienteDAO.updatePaciente(pacienteNuevo);
        
        if (!msj.isError()) {
            Utilidades.mostrarAlertaSimple("Paciente editado", msj.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void btnCancel(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
}
