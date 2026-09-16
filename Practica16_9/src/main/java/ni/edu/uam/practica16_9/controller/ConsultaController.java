package ni.edu.uam.practica16_9.controller;

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
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreCompleto()));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

        tabla.setItems(DataRepository.getClientes());
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

    @FXML
    public void volver(ActionEvent event) {
        // Método listo para vincular la navegación de regreso al menú principal
    }
}