package ni.edu.uam.practica16_9.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ni.edu.uam.practica16_9.application.MainApp;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MainMenuController {

    @FXML
    private Label lblEstadoCarpeta;

    @FXML
    private VBox panelContextual;

    @FXML
    public void initialize() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem itemResumen = new MenuItem("Ver Resumen del Sistema");
        itemResumen.setOnAction(e -> mostrarInfoContextual());

        contextMenu.getItems().add(itemResumen);

        if (panelContextual != null) {
            panelContextual.setOnContextMenuRequested(event ->
                    contextMenu.show(panelContextual, event.getScreenX(), event.getScreenY())
            );
        }
    }

    @FXML
    private void mostrarInfoContextual() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Resumen del Sistema");
        dialog.setHeaderText("Módulo de Gestión de Clientes y Solicitudes");
        dialog.setContentText(
                "Estado del Sistema:\n" +
                        "• Módulo Principal: Activo\n" +
                        "• Base de Datos: Conectada\n" +
                        "• Permisos: Registro y Consulta habilitados"
        );
        dialog.showAndWait();
    }

    @FXML
    private void irRegistro(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.cambiarVentana(stage, "/ni/edu/uam/practica16_9/view/registro-view.fxml", "Registro de Cliente");
    }

    @FXML
    private void irConsulta(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.cambiarVentana(stage, "/ni/edu/uam/practica16_9/view/consulta-view.fxml", "Consulta de Clientes");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea cerrar sesión y volver al login?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cambiarVentana(event, "/ni/edu/uam/practica16_9/view/login-view.fxml", "Inicio de Sesión");
        }
    }

    @FXML
    private void seleccionarCarpeta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar carpeta de trabajo");

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        File carpetaSeleccionada = directoryChooser.showDialog(stage);

        if (carpetaSeleccionada != null) {
            lblEstadoCarpeta.setText("Carpeta activa: " + carpetaSeleccionada.getAbsolutePath());

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Carpeta Seleccionada");
            info.setHeaderText(null);
            info.setContentText("Ruta configurada correctamente:\n" + carpetaSeleccionada.getAbsolutePath());
            info.showAndWait();
        }
    }

    @FXML
    private void mostrarInfoContextual(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Información del ContextMenu");
        dialog.setHeaderText("Acción de Menú Contextual");
        dialog.setContentText("Has activado la acción del ContextMenu asociada al panel de la pantalla principal.");
        dialog.showAndWait();
    }

    @FXML
    private void onPanelClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evento de Ratón");
            alert.setHeaderText("Doble Clic Detectado");
            alert.setContentText("Has hecho doble clic en el panel interactivo.");
            alert.showAndWait();
        }
    }



    @FXML
    private void onKeyPressedEsc(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar salida");
            alert.setHeaderText("Tecla ESC presionada");
            alert.setContentText("¿Deseas regresar a la pantalla de inicio de sesión?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                cargarEscena(stage, "/ni/edu/uam/practica16_9/view/login-view.fxml", "Inicio de Sesión");
            }
        }
    }

    private void cambiarVentana(ActionEvent event, String fxmlPath, String titulo) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        cargarEscena(stage, fxmlPath, titulo);
    }

    private void cargarEscena(Stage stage, String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de carga");
            alert.setHeaderText("No se pudo abrir la vista");
            alert.setContentText("Verifica que la ruta FXML exista: " + fxmlPath);
            alert.showAndWait();
        }
    }
}