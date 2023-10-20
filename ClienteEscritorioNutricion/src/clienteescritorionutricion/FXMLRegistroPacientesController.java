/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion;

import clienteescritorionutricion.modelo.dao.PacienteDAO;
import clienteescritorionutricion.modelo.pojo.Medico;
import clienteescritorionutricion.modelo.pojo.Mensaje;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLRegistroPacientesController implements Initializable {
    
    private Paciente paciente;
    private int idMedico;
    private String sexo;

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfEstatura;
    @FXML
    private TextField tfTallaInicial;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefono;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorApellidoPaterno;
    @FXML
    private Label lbErrorApellidoMaterno;
    @FXML
    private Label lbErrorFechaNacimiento;
    @FXML
    private Label lbErrorPeso;
    @FXML
    private Label lbErrorEstatura;
    @FXML
    private Label lbErrorTalla;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorTelefono;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private ToggleGroup tgSexo;
    @FXML
    private RadioButton rbFemenino;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private Pane btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tgSexo.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (rbFemenino.isSelected()) {
                    sexo = "F";
                } else{
                    sexo = "M";
                }
            }
            
        });
    }

    public void inicializarInformacion(Paciente paciente, int idMedico){
        this.paciente = paciente;
        this.idMedico = idMedico;
        
        if (paciente != null){
            
        }
    }

    @FXML
    private void btnRegistrarPaciente(ActionEvent event) {
        Paciente pacienteNuevo = new Paciente();
        pacienteNuevo.setNombre(tfNombre.getText());
        pacienteNuevo.setApellidoPaterno(tfApellidoPaterno.getText());
        pacienteNuevo.setApellidoMaterno(tfApellidoMaterno.getText());
        pacienteNuevo.setFechaNacimiento(dpFechaNacimiento.getValue().toString());
        pacienteNuevo.setSexo(sexo);
        pacienteNuevo.setPeso(Float.parseFloat(tfPeso.getText()));
        pacienteNuevo.setEstatura(Float.parseFloat(tfEstatura.getText()));
        pacienteNuevo.setTallaInicial(Integer.parseInt(tfTallaInicial.getText()));
        pacienteNuevo.setEmail(tfEmail.getText());
        pacienteNuevo.setTelefono(tfTelefono.getText());
        pacienteNuevo.setPassword(pfPassword.getText());
        pacienteNuevo.setIdMedico(idMedico);
        
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

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }
}
