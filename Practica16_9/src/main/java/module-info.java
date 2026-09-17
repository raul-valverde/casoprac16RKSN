module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;


    exports ni.edu.uam.practica16_9.application;
    opens ni.edu.uam.practica16_9.application to javafx.fxml;


    exports ni.edu.uam.practica16_9.controller;
    opens ni.edu.uam.practica16_9.controller to javafx.fxml;
}