package ni.edu.uam.practica16_9.repository;

import ni.edu.uam.practica16_9.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataRepository {
    private static final ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getClientes() {
        return clientes;
    }
}