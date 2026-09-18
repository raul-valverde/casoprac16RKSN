module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    // Permisos FXML para controladores y vistas de todo el equipo
    opens ni.edu.uam.practica16_9 to javafx.fxml;
    opens ni.edu.uam.practica16_9.appplication to javafx.fxml;
    opens ni.edu.uam.practica16_9.controller to javafx.fxml;

    // Permiso para que TableView lea los datos del modelo
    opens ni.edu.uam.practica16_9.model to javafx.base, javafx.fxml;

    // Exportación de paquetes compartidos
    exports ni.edu.uam.practica16_9;
    exports ni.edu.uam.practica16_9.appplication;
    exports ni.edu.uam.practica16_9.controller;
    exports ni.edu.uam.practica16_9.model;
}