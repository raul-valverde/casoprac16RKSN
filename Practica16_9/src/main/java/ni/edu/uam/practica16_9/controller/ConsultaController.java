package ni.edu.uam.practica16_9.controller;

import ni.edu.uam.practica16_9.appplication.MainApp;
import ni.edu.uam.practica16_9.model.Cliente;
import ni.edu.uam.practica16_9.repository.DataRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ConsultaController {

    @FXML private TableView<Cliente> tabla;
    @FXML private TableColumn<Cliente, String> colNombre, colTipo, colCiudad, colSolicitud;
    @FXML private TableColumn<Cliente, LocalDate> colFecha;

    @FXML
    public void initialize() {
        // Vinculación de columnas
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreCompleto()));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

        // Carga de datos de prueba si la lista está vacía
        if (DataRepository.getClientes().isEmpty()) {
            DataRepository.getClientes().add(new Cliente("Raúl", "Pérez", "VIP", "Managua", "Soporte", "Desarrollo", "", LocalDate.of(1999, 4, 15)));
            DataRepository.getClientes().add(new Cliente("Kellys", "López", "Regular", "León", "Consultoría", "Capacitación", "", LocalDate.of(2001, 8, 22)));
        }

        tabla.setItems(DataRepository.getClientes());
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            volverAlMenu();
        }
    }

    @FXML
    public void volver(ActionEvent event) {
        volverAlMenu();
    }

    private void volverAlMenu() {
        Stage stage = (Stage) tabla.getScene().getWindow();
        MainApp.cambiarVentana(stage, "/ni/edu/uam/practica16_9/view/MainView.fxml", "Menú Principal");
    }

    @FXML
    public void onTablaClick(MouseEvent event) {
        if (event.getClickCount() == 2 && tabla.getSelectionModel().getSelectedItem() != null) {
            abrirDetalle(tabla.getSelectionModel().getSelectedItem());
        }
    }

    private void abrirDetalle(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/practica16_9/view/DetalleView.fxml"));
            Parent root = loader.load();

            // Paso de datos al controlador receptor
            DetalleController controller = loader.getController();
            controller.setCliente(cliente);

            Stage dialog = new Stage();
            dialog.setTitle("Detalle del Cliente");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}