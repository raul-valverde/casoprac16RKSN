module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;

    // Permite que JavaFX acceda a tus vistas, controladores y clase principal
    opens ni.edu.uam.practica16_9.appplication to javafx.graphics, javafx.fxml;
    opens ni.edu.uam.practica16_9.controller to javafx.fxml;
    opens ni.edu.uam.practica16_9.model to javafx.fxml;

    exports ni.edu.uam.practica16_9.appplication;
    exports ni.edu.uam.practica16_9.controller;
}