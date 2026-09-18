module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.practica16_9.application to javafx.graphics, javafx.fxml;
    opens ni.edu.uam.practica16_9.controller to javafx.fxml;
    opens ni.edu.uam.practica16_9.model to javafx.base, javafx.fxml;

    exports ni.edu.uam.practica16_9;
    exports ni.edu.uam.practica16_9.application;
    exports ni.edu.uam.practica16_9.controller;
    exports ni.edu.uam.practica16_9.model;
    exports ni.edu.uam.practica16_9.repository;
}