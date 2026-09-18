package ni.edu.uam.practica16_9.controller;

import ni.edu.uam.practica16_9.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetalleController {

    @FXML private Label lblNombre, lblTipo, lblCiudad, lblFecha, lblSolicitud, lblServicios;
    @FXML private ImageView imgFoto;

    public void setCliente(Cliente cliente) {
        if (cliente == null) return;
        if (lblNombre != null) lblNombre.setText(cliente.getNombreCompleto());
        if (lblTipo != null) lblTipo.setText(cliente.getTipoCliente());
        if (lblCiudad != null) lblCiudad.setText(cliente.getCiudad());
        if (lblFecha != null) lblFecha.setText(cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : "");
        if (lblSolicitud != null) lblSolicitud.setText(cliente.getTipoSolicitud());
        if (lblServicios != null) lblServicios.setText(cliente.getServicios() != null ? cliente.getServicios() : "Ninguno");

        if (imgFoto != null && cliente.getFotoPath() != null && !cliente.getFotoPath().isEmpty()) {
            try {
                imgFoto.setImage(new Image(cliente.getFotoPath()));
            } catch (Exception ignored) {
                // Si la ruta de imagen no es válida o local, la ventana se abre normalmente sin romper la app
            }
        }
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }
}