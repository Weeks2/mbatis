/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion;

import clienteescritorionutricion.modelo.dao.PacienteDAO;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLAdminPacientesController implements Initializable {
    
    private Paciente paciente;
    private int idMedico;
    private ObservableList<Paciente> pacientesMedico;

    @FXML
    private TableView<Paciente> tvPacientes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colFechaNacimiento;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pacientesMedico = FXCollections.observableArrayList();
        configurarColumnasTabla();
    }

    public void inicializarInformacion(int idMedico) {
        this.idMedico = idMedico;
        consultarInformacionPacientes();
    }

    @FXML
    private void btnIrFormularioRegistro(ActionEvent event) {
        irFormularioPaciente(null, idMedico);
    }

    @FXML
    private void btnFormularioModificar(ActionEvent event) throws IOException {
        int posisionSeleccionada = tvPacientes.getSelectionModel().getSelectedIndex();

        if (posisionSeleccionada != -1) {
            Paciente paciente = pacientesMedico.get(posisionSeleccionada);
            //Utilidades.mostrarAlertaSimple("Paciente", paciente.getNombre() + " " + paciente.getApellidoPaterno() + " " + paciente.getApellidoMaterno(), Alert.AlertType.INFORMATION);
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLEditarPaciente.fxml"));
            Parent vista = vistaLoad.load();

            FXMLEditarPacienteController controlador = vistaLoad.getController();
            controlador.inicializarInformacionPaciente(paciente);

            Stage stage = new Stage();

            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Editar Paciente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        } else {
            Utilidades.mostrarAlertaSimple("Seleccion del Paciente", "Para modificar debes seleccionar un paciente de una tabla.", Alert.AlertType.WARNING);
        }
    }

    private void consultarInformacionPacientes() {
        HashMap<String, Object> respuesta = PacienteDAO.obtenerPacientesPorMedico(idMedico);
        if (!(boolean) respuesta.get("error")) {
            List<Paciente> pacientes = (List<Paciente>) respuesta.get("pacientes");
            pacientesMedico.addAll(pacientes);
            tvPacientes.setItems(pacientesMedico);
        } else {
            Utilidades.mostrarAlertaSimple("Error", (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    private void irFormularioPaciente(Paciente paciente, int idMedico){
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLRegistroPacientes.fxml"));
            Parent vista = vistaLoad.load();

            FXMLRegistroPacientesController controlador = vistaLoad.getController();
            controlador.inicializarInformacion(paciente, idMedico);

            Stage stage = new Stage();

            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Reguistro de Pacientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
        }
    }

    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    }
}