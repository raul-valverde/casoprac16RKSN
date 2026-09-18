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
    // Agrégala justo antes del public void initialize()
    private String rutaImagenSeleccionada = "";

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
            // Guarda la ruta en texto para la base de datos temporal
            rutaImagenSeleccionada = archivo.toURI().toString();
            Image imagen = new Image(rutaImagenSeleccionada);
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
    public void guardarRegistro(ActionEvent event) {
        // 1. Validación original
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() ||
                cmbTipoCliente.getValue() == null || cmbCiudad.getValue() == null) {

            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error de Validación");
            alertaError.setHeaderText(null);
            alertaError.setContentText("Por favor, complete todos los campos obligatorios.");
            alertaError.showAndWait();
            return;
        }

        // 2. Recopilar servicios seleccionados
        StringBuilder servicios = new StringBuilder();
        if (chkInternet.isSelected()) servicios.append("Internet ");
        if (chkCable.isSelected()) servicios.append("Cable TV ");
        if (chkTelefonia.isSelected()) servicios.append("Telefonía ");

        // 3. Determinar tipo de solicitud
        RadioButton seleccionado = (RadioButton) grupoSolicitud.getSelectedToggle();
        String tipoSolicitud = seleccionado != null ? seleccionado.getText() : "Nuevo";

        // 4. Crear objeto Cliente importando la clase desde su paquete correspondiente
        ni.edu.uam.practica16_9.model.cliente.Cliente nuevoCliente = new ni.edu.uam.practica16_9.model.cliente.Cliente(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                tipoSolicitud,
                servicios.toString().trim(),
                rutaImagenSeleccionada,
                dpFechaNacimiento.getValue()
        );

        // 5. Agregarlo al repositorio global de Raúl
        ni.edu.uam.practica16_9.repository.DataRepository.getClientes().add(nuevoCliente);

        // 6. Alerta de éxito y limpieza
        Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
        alertaExito.setTitle("Éxito");
        alertaExito.setHeaderText(null);
        alertaExito.setContentText("Cliente registrado correctamente y enviado al menú principal.");
        alertaExito.showAndWait();

        limpiarFormulario(null);
    }

    @FXML
    public void accionSalir() {
        javafx.application.Platform.exit();
    }
    @FXML
    public void accionRegresar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Estás seguro que deseas regresar? Se perderán los datos no guardados.");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            // Captura la ventana actual sin depender de un botón específico
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Usa el método centralizado de Raúl para ir al menú
            ni.edu.uam.practica16_9.application.MainApp.cambiarVentana(stage, "/ni/edu/uam/practica16_9/view/main-menu-view.fxml", "Menú Principal");
        }
    }
}