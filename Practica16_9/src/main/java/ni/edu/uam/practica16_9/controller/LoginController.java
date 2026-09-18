package ni.edu.uam.practica16_9.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;


    //evento ActionEvent para el botón de Iniciar Sesión
    @FXML
    private void onIniciarSesionClick(ActionEvent event) {
        procesarInicioSesion(event);
    }

    //evento ActionEvent para el botón Salir
    @FXML
    private void onSalirClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de salida");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea salir del sistema?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    //evento KeyEvent para detectar la tecla enter
    @FXML
    private void onKeyPressedPassword(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            procesarInicioSesion(event);
        }
    }

    private void procesarInicioSesion(Object eventSource) {
        String usuario = txtUsuario.getText() != null ? txtUsuario.getText().trim() : "";
        String password = txtPassword.getText() != null ? txtPassword.getText().trim() : "";

        // validar campos vacíos
        if (usuario.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Incompletos");
            alert.setHeaderText("Información Requerida");
            alert.setContentText("Por favor, ingrese tanto el usuario como la contraseña.");
            alert.showAndWait();
            return;
        }

        // validacion de credenciales
        if (usuario.equals("admin") && password.equals("1234")) {
            abrirVentanaPrincipal(eventSource);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Autenticación");
            alert.setHeaderText("Credenciales Incorrectas");
            alert.setContentText("El usuario o la contraseña son incorrectos.");
            alert.showAndWait();
        }
    }

    private void abrirVentanaPrincipal(Object eventSource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/practica16_9/view/main-menu-view.fxml"));
            Parent root = loader.load();

            Stage stage;
            if (eventSource instanceof ActionEvent) {
                stage = (Stage) ((Node) ((ActionEvent) eventSource).getSource()).getScene().getWindow();
            } else {
                stage = (Stage) txtPassword.getScene().getWindow();
            }

            stage.setScene(new Scene(root));
            stage.setTitle("Sistema de Gestión - Menú Principal");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Navegación");
            alert.setHeaderText("No se pudo cargar la ventana principal");
            alert.setContentText("Asegúrese de que el archivo main-menu-view.fxml existe en la carpeta view.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}