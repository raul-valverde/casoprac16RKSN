package ni.edu.uam.practica16_9.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.practica16_9.model.Cliente;

public class DataRepository {
    private static final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getClientes() {
        return listaClientes;
    }
}