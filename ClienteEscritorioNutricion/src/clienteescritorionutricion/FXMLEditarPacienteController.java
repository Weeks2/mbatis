/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion;

import clienteescritorionutricion.modelo.pojo.Paciente;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
        tfEmail.setText(infoPaciente.getEmail());
        tfTelefono.setText(infoPaciente.getTelefono());
    }
}
