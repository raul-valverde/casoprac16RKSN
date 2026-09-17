module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.practica16_9 to javafx.fxml;
    exports ni.edu.uam.practica16_9;


    exports ni.edu.uam.practica16_9.controller;
    opens ni.edu.uam.practica16_9.controller to javafx.fxml;


    exports ni.edu.uam.practica16_9.model;
    opens ni.edu.uam.practica16_9.model to javafx.fxml;
}
