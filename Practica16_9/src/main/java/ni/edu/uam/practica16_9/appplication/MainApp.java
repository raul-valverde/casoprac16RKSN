package ni.edu.uam.practica16_9.appplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/ni/edu/uam/practica16_9/view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Sistema de Gestión - Inicio de Sesión");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}