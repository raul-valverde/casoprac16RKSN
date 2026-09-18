package ni.edu.uam.practica16_9.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegistroController {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbCiudad;
    @FXML private DatePicker dpFechaNacimiento;

    @FXML private ToggleGroup grupoSolicitud;
    @FXML private RadioButton rbNuevo;
    @FXML private RadioButton rbRenovacion;

    @FXML private CheckBox chkInternet;
    @FXML private CheckBox chkCable;
    @FXML private CheckBox chkTelefonia;

    @FXML private ImageView imgFoto;
    @FXML private Button btnSeleccionarFoto;
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCancelar;

    @FXML
    public void initialize() {
        cmbTipoCliente.getItems().addAll("Residencial", "Corporativo", "VIP");
        cmbCiudad.getItems().addAll("Managua", "León", "Granada", "Masaya", "Estelí");
    }

    @FXML
    void seleccionarFotografia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Fotografía");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) btnSeleccionarFoto.getScene().getWindow();
        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            Image imagen = new Image(archivo.toURI().toString());
            imgFoto.setImage(imagen);
        }
    }

    @FXML
    void limpiarFormulario(ActionEvent event) {
        txtNombres.clear();
        txtApellidos.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCiudad.getSelectionModel().clearSelection();
        dpFechaNacimiento.setValue(null);
        rbNuevo.setSelected(true);
        chkInternet.setSelected(false);
        chkCable.setSelected(false);
        chkTelefonia.setSelected(false);
        imgFoto.setImage(null);
    }


    @FXML
    void guardarRegistro(ActionEvent event) {
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() ||
                cmbTipoCliente.getValue() == null || cmbCiudad.getValue() == null) {

            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error de Validación");
            alertaError.setHeaderText(null);
            alertaError.setContentText("Por favor, complete todos los campos obligatorios (Nombres, Apellidos, Tipo, Ciudad).");
            alertaError.showAndWait();
            return;
        }

        Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
        alertaExito.setTitle("Éxito");
        alertaExito.setHeaderText(null);
        alertaExito.setContentText("Cliente registrado correctamente.");
        alertaExito.showAndWait();

        limpiarFormulario(null);
    }

    @FXML
    public void accionSalir() {
        javafx.application.Platform.exit();
    }
    @FXML
    public void accionRegresar(javafx.event.ActionEvent event){
        try {
        // IMPORTANTE: Cambia "menu-view.fxml" por el nombre real de tu archivo del menú
        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("menu-view.fxml"));
        javafx.scene.Scene scene = new javafx.scene.Scene(fxmlLoader.load());

        // Obtiene la ventana actual y le pone la nueva escena del menú
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }}
}